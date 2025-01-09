package com.an.github.data.remote.api

import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class GithubApiServiceTest {
    private val mockWebServer = MockWebServer()
    private lateinit var apiService: GithubApiService

    @Before
    fun setup() {
        mockWebServer.start()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(GithubApiService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `read sample success json file`() {
        val reader = MockResponseFileReader("api-response/test_repositories.json")
        assertNotNull(reader.content)
    }

    @Test
    fun `fetch github repositories and check response Code 200 returned`() = runTest {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("api-response/test_repositories.json").content)

        mockWebServer.enqueue(response)

        // Act
        val actualResponse = apiService.fetchRepositories(
            "top_rated", 20, 10
        )

        // Assert
        assertEquals(806201, actualResponse.totalCount)
        assertEquals(10, actualResponse.items.size)
    }
}
