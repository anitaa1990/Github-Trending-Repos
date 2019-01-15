package com.an.github.data.remote.interceptor

import com.an.github.AppConstants
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()
        val url = originalUrl.newBuilder()
                .addQueryParameter("q", AppConstants.QUERY_API)
                .addQueryParameter("per_page", AppConstants.PAGE_MAX_SIZE)
                .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
