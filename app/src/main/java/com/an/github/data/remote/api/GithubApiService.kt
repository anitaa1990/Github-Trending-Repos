package com.an.github.data.remote.api

import com.an.github.data.remote.model.GithubApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {

    @GET("search/repositories?sort=stars&order=desc")
    suspend fun fetchRepositories(
        @Query("q") query: String,
        @Query("page") page: Long,
        @Query("per_page") perPage: Long
    ): GithubApiResponse
}
