package com.an.github.data.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.an.github.data.local.FilterStore
import com.an.github.data.local.entity.GithubEntity
import com.an.github.data.local.entity.GithubRemoteKey
import com.an.github.data.remote.model.GithubFilter
import com.an.github.data.remote.model.equalTo
import com.an.github.data.repository.GithubRepository
import kotlinx.coroutines.flow.firstOrNull
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.collections.firstOrNull

@OptIn(ExperimentalPagingApi::class)
class GithubRemoteMediator @Inject constructor(
    private val filter: GithubFilter,
    private val filterStore: FilterStore,
    private val repository: GithubRepository
) : RemoteMediator<Int, GithubEntity>() {
    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
        val currentCategory = filterStore.githubFilter.firstOrNull()

        return if (
            System.currentTimeMillis() - (repository.getRemoteKeyCreatedTime() ?: 0) < cacheTimeout
            && filter.equalTo(currentCategory)
        ) {
            // Cached data is up-to-date, so there is no need to re-fetch from the network.
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            // Need to refresh cached data from network; returning
            // LAUNCH_INITIAL_REFRESH here will also block RemoteMediator's
            // APPEND and PREPEND from running until REFRESH succeeds.
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    /**.
     *
     * @param state This gives us information about the pages that were loaded before,
     * the most recently accessed index in the list, and the PagingConfig we defined when initializing the paging stream.
     * @param loadType this tells us whether we need to load data at the end (LoadType.APPEND)
     * or at the beginning of the data (LoadType.PREPEND) that we previously loaded,
     * or if this the first time we're loading data (LoadType.REFRESH).
     */
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GithubEntity>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                //New Query so clear the DB
                val remoteKeys = state.getRemoteKeyClosestToCurrentPosition()
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = state.getRemoteKeyForFirstItem()
                // If remoteKeys is null, that means the refresh result is not in the database yet.
                val prevKey = remoteKeys?.prevKey
                prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
            LoadType.APPEND -> {
                val remoteKeys = state.getRemoteKeyForLastItem()

                // If remoteKeys is null, that means the refresh result is not in the database yet.
                // We can return Success with endOfPaginationReached = false because Paging
                // will call this method again if RemoteKeys becomes non-null.
                // If remoteKeys is NOT NULL but its nextKey is null, that means we've reached
                // the end of pagination for append.
                val nextKey = remoteKeys?.nextKey
                nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {
            val apiResponse = repository.fetchRepositories(
                filter = filter,
                nextPage = page.toLong()
            )

            val repositories = apiResponse.items
            val endOfPaginationReached = repositories.isEmpty()

            if (loadType == LoadType.REFRESH) {
                repository.clearRemoteKeys()
                repository.deleteAll()
            }

            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (endOfPaginationReached) null else page + 1
            val remoteKeys = repositories.map {
                GithubRemoteKey(
                    githubId = it.remoteId,
                    prevKey = prevKey,
                    currentPage = page,
                    nextKey = nextKey
                )
            }

            repository.addRemoteKeys(remoteKeys)
            repository.addRepositories(repositories)
            filterStore.storeGithubFilter(filter)

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: IOException) {
            error.printStackTrace()
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            error.printStackTrace()
            return MediatorResult.Error(error)
        } catch (error: Exception) {
            error.printStackTrace()
            return MediatorResult.Error(error)
        }
    }

    /** LoadType.Append
     * When we need to load data at the end of the currently loaded data set, the load parameter is
     * LoadType.APPEND
     */
    private suspend fun PagingState<Int, GithubEntity>.getRemoteKeyForLastItem(): GithubRemoteKey? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return this.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { repo ->
            repository.getRemoteKeyByGithubId(repo.remoteId)
        }
    }

    /** LoadType.Prepend
     * When we need to load data at the beginning of the currently loaded data set, the load
     * parameter is LoadType.PREPEND
     */
    private suspend fun PagingState<Int, GithubEntity>.getRemoteKeyForFirstItem(): GithubRemoteKey? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return this.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { repo ->
            repository.getRemoteKeyByGithubId(repo.remoteId)
        }
    }

    /** LoadType.REFRESH
     * Gets called when it's the first time we're loading data, or when PagingDataAdapter.refresh()
     * is called; so now the point of reference for loading our data is the state.anchorPosition.
     * If this is the first load, then the anchorPosition is null. When PagingDataAdapter.refresh()
     * is called, the anchorPosition is the first visible position in the displayed list, so we
     * will need to load the page that contains that specific item.
     */
    private suspend fun PagingState<Int, GithubEntity>.getRemoteKeyClosestToCurrentPosition(): GithubRemoteKey? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return this.anchorPosition?.let { position ->
            this.closestItemToPosition(position)?.remoteId?.let { id ->
                repository.getRemoteKeyByGithubId(id)
            }
        }
    }
}
