package com.an.github.api

import java.io.IOException
import java.nio.charset.StandardCharsets
import okio.BufferedSource
import okio.Okio
import okio.Source
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//@RunWith(JUnit4::class)
open class ApiAbstract<T> {

//    private lateinit var mockWebServer: MockWebServer
//
//
//    @Before
//    @Throws(IOException::class)
//    fun mockServer() {
//        mockWebServer = MockWebServer()
//        mockWebServer.start()
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun stopServer() {
//        mockWebServer.shutdown()
//    }
//
//    @Throws(IOException::class)
//    fun enqueueResponse(fileName: String) {
//        enqueueResponse(fileName, emptyMap())
//    }
//
//
//    @Throws(IOException::class)
//    private fun enqueueResponse(fileName: String, headers: Map<String, String>) {
//        val inputStream = ApiAbstract::class.java.classLoader.getResourceAsStream(String.format("api-response/%s", fileName))
//        val source = Okio.buffer(Okio.source(inputStream))
//        val mockResponse = MockResponse()
//        for ((key, value) in headers) {
//            mockResponse.addHeader(key, value)
//        }
//        mockWebServer.enqueue(mockResponse.setBody((source as BufferedSource).readString(StandardCharsets.UTF_8)))
//    }
//
//    fun createService(clazz: Class<T>): T {
//        return Retrofit.Builder()
//                .baseUrl(mockWebServer.url("/"))
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build()
//                .create(clazz)
//    }
//
//    @Throws(InterruptedException::class)
//    fun assertRequestPath(path: String) {
//        val request = mockWebServer.takeRequest()
//        MatcherAssert.assertThat(request.path, CoreMatchers.`is`(path))
//    }
}
