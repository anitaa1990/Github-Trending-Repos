package com.an.github.data.repository

import com.an.github.AppConstants.PAGE_SIZE
import com.an.github.data.local.dao.GithubDao
import com.an.github.data.local.dao.GithubRemoteKeyDao
import com.an.github.data.local.entity.GithubEntity
import com.an.github.data.local.entity.GithubRemoteKey
import com.an.github.data.remote.api.GithubApiService
import com.an.github.data.remote.model.GithubApiResponse
import com.an.github.data.remote.model.GithubFilter
import com.an.github.data.remote.model.api
import javax.inject.Inject


class GithubRepository @Inject constructor(
    private val githubDao: GithubDao,
    private val remoteKeyDao: GithubRemoteKeyDao,
    private val githubApiService: GithubApiService
) {

    suspend fun fetchRepositories(
        filter: GithubFilter,
        nextPage: Long,
        perPage: Long = PAGE_SIZE.toLong()
    ): GithubApiResponse {
        return try {
            val response = githubApiService.fetchRepositories(
                query = filter.api(),
                page = nextPage,
                perPage = perPage
            )
            response
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception(e)
        }
    }

    fun getRepositories() = githubDao.getRepositories()

    fun getRepository(remoteId: Long) = githubDao.getRepository(remoteId)

    suspend fun addRepositories(repositories: List<GithubEntity>) = githubDao.insertRepositories(repositories)

    suspend fun deleteAll() = githubDao.clearAll()

    fun getPagingSource() = githubDao.pagingSource()

    suspend fun addRemoteKeys(remoteKeys: List<GithubRemoteKey>) = remoteKeyDao.insertAll(remoteKeys)

    suspend fun getRemoteKeyCreatedTime() = remoteKeyDao.getCreationTime()

    suspend fun getRemoteKeyByGithubId(remoteId: Long) = remoteKeyDao.getRemoteKeyByGithubId(remoteId)

    suspend fun clearRemoteKeys() = remoteKeyDao.clearRemoteKeys()
}
