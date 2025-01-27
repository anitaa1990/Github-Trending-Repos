package com.an.github.data.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.an.github.data.local.AppDatabase
import com.an.github.data.local.FilterStore
import com.an.github.data.local.dao.GithubDao
import com.an.github.data.local.dao.GithubRemoteKeyDao
import com.an.github.data.local.entity.GithubEntity
import com.an.github.data.remote.api.GithubApiService
import com.an.github.data.remote.model.GithubApiResponse
import com.an.github.data.remote.model.GithubFilter
import com.an.github.data.remote.model.api
import com.an.github.data.repository.GithubRepository
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalPagingApi
@RunWith(AndroidJUnit4::class)
class GithubRemoteMediatorTest: TestCase() {
    private lateinit var db: AppDatabase
    private lateinit var filterStore: FilterStore
    private val apiService: GithubApiService = mock()

    private lateinit var repository: GithubRepository
    private val mockList = getRepositories()

    @Before
    public override fun setUp() {
        // get context -- since this is an instrumental test it requires
        // context from the running application
        // initialize the db and dao variable
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        val dao: GithubDao = db.githubDao
        val remoteKeyDao: GithubRemoteKeyDao = db.githubKeyDao
        filterStore = FilterStore(ApplicationProvider.getApplicationContext())
        repository = GithubRepository(dao, remoteKeyDao, apiService)
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runBlocking {
        // Add mock results for the API to return
        val filter = GithubFilter.Android
        `when`(apiService.fetchRepositories(filter.api(), 1L, 20)).thenReturn(
            GithubApiResponse(
                totalCount = 20L, items = mockList
            )
        )

        val remoteMediator = GithubRemoteMediator(
            repository = repository,
            filter = filter,
            filterStore = filterStore
        )
        val pagingState = PagingState<Int, GithubEntity>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue( result is RemoteMediator.MediatorResult.Success )
        assertFalse( (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached )
    }

    @Test
    fun refreshLoadSuccessAndEndOfPaginationWhenNoMoreData() = runBlocking {
        val filter = GithubFilter.Android

        // To test endOfPaginationReached, don't set up the mockApi to return post data here
        `when`(apiService.fetchRepositories(filter.api(), 1L, 20)).thenReturn(
            GithubApiResponse(
                totalCount = 0, items = emptyList()
            )
        )

        val remoteMediator = GithubRemoteMediator(
            repository = repository,
            filter = filter,
            filterStore = filterStore
        )
        val pagingState = PagingState<Int, GithubEntity>(
            listOf(),
            null,
            PagingConfig(20),
            100
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue ( result is RemoteMediator.MediatorResult.Success )
        assertTrue ( (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached )
    }

    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() = runBlocking {
        // By default, when apiService is called, the result will be null so an exception will be
        // thrown so we don't need to simulate error exception here

        val remoteMediator = GithubRemoteMediator(
            repository = repository,
            filter = GithubFilter.Android,
            filterStore = filterStore
        )
        val pagingState = PagingState<Int, GithubEntity>(
            listOf(),
            null,
            PagingConfig(20),
            20
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue ( result is RemoteMediator.MediatorResult.Error )
    }

    private fun getRepositories(): List<GithubEntity> {
        return listOf<GithubEntity>(
            GithubEntity(
                remoteId = 12,
                name = "flutter",
                fullName = "",
                avatarUrl = "",
                htmlUrl = "",
                description = "",
                contributorsUrl = "",
                createdAt = "",
                starsCount = 0L,
                language = "",
                forks = 0L,
                watchers = 0L
            ),
            GithubEntity(
                remoteId = 14,
                name = "free-programming-books-zh_CN",
                fullName = "",
                avatarUrl = "",
                htmlUrl = "",
                description = "",
                contributorsUrl = "",
                createdAt = "",
                starsCount = 0L,
                language = "",
                forks = 0L,
                watchers = 0L
            ),
            GithubEntity(
                remoteId = 15,
                name = "material-design-icons",
                fullName = "",
                avatarUrl = "",
                htmlUrl = "",
                description = "",
                contributorsUrl = "",
                createdAt = "",
                starsCount = 0L,
                language = "",
                forks = 0L,
                watchers = 0L
            )
        )
    }
}
