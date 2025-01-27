package com.an.github.di

import android.content.Context
import androidx.room.Room
import com.an.github.data.local.AppDatabase
import com.an.github.data.local.dao.GithubDao
import com.an.github.data.local.dao.GithubRemoteKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GithubDbModule {
    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "github_database"
        ).build()
    }

    @Provides
    fun provideMovieDao(database: AppDatabase): GithubDao {
        return database.githubDao
    }

    @Provides
    fun provideGithubRemoteKeyDao(database: AppDatabase): GithubRemoteKeyDao {
        return database.githubKeyDao
    }
}
