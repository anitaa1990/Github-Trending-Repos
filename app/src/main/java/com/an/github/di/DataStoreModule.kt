package com.an.github.di

import android.content.Context
import com.an.github.data.local.FilterStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideFilterStore(
        @ApplicationContext context: Context
    ): FilterStore = FilterStore(context)
}
