package com.an.github.di.module

import android.app.Application

import com.an.github.data.remote.api.GithubApiService
import com.an.github.data.remote.interceptor.RequestInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import java.io.File
import java.util.concurrent.TimeUnit

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

//@Module
class ApiModule {

//    @Provides
//    @Singleton
//    internal fun provideGson(): Gson {
//        val gsonBuilder = GsonBuilder()
//        return gsonBuilder.create()
//    }
//
//
//    @Provides
//    @Singleton
//    internal fun provideCache(application: Application): Cache {
//        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
//        val httpCacheDirectory = File(application.cacheDir, "http-cache")
//        return Cache(httpCacheDirectory, cacheSize)
//    }
//
//
//    @Provides
//    @Singleton
//    internal fun provideOkhttpClient(cache: Cache): OkHttpClient {
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BASIC
//
//        val httpClient = OkHttpClient.Builder()
//        httpClient.cache(cache)
//        httpClient.addInterceptor(logging)
//        httpClient.addNetworkInterceptor(RequestInterceptor())
//        httpClient.connectTimeout(30, TimeUnit.SECONDS)
//        httpClient.readTimeout(30, TimeUnit.SECONDS)
//        return httpClient.build()
//    }


//    @Provides
//    @Singleton
//    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .baseUrl("https://api.github.com/")
//                .client(okHttpClient)
//                .build()
//    }


//    @Provides
//    @Singleton
//    internal fun provideGithubApiService(retrofit: Retrofit): GithubApiService {
//        return retrofit.create(GithubApiService::class.java)
//    }
}
