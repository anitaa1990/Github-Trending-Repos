package com.an.github.data.repository

import com.an.github.data.local.dao.GithubDao
import com.an.github.data.local.entity.GithubEntity
import com.an.github.data.remote.api.GithubApiService
import com.an.github.data.remote.model.GithubApiResponse
import com.an.github.data.remote.model.GithubFilter
import com.an.github.data.remote.model.api
import javax.inject.Inject


class GithubRepository @Inject constructor(
    private val githubDao: GithubDao,
    private val githubApiService: GithubApiService
) {

    suspend fun fetchRepositories(
        filter: GithubFilter,
        nextPage: Long,
        perPage: Long
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
}
