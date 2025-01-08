package com.an.github.api

import com.an.github.data.remote.api.GithubApiService

import java.io.IOException

import retrofit2.Response

class GithubApiServiceTest : ApiAbstract<GithubApiService>() {

    private lateinit var service: GithubApiService

//    @Before
//    fun initService() {
//        this.service = createService(GithubApiService::class.java)
//    }
//
//    @Test
//    @Throws(IOException::class)
//    fun fetchPostsTest() {
//        enqueueResponse("test_repositories.json")
//        val response = service.fetchRepositories("stars", "desc", 1L).blockingFirst()
//        Assert.assertEquals(true, response.isSuccessful)
//
//        val apiResponse = response.body()
//        Assert.assertEquals(806201L, apiResponse?.totalCount)
//        Assert.assertEquals(10, apiResponse?.items?.size)
//    }
}
