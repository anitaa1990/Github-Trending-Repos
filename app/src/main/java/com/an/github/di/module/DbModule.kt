package com.an.github.di.module


import android.app.Application
import android.arch.persistence.room.Room

import com.an.github.data.local.AppDatabase
import com.an.github.data.local.dao.GithubDao

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class DbModule {

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application,
                AppDatabase::class.java, "Github.db")
                .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    internal fun provideGithubDao(appDatabase: AppDatabase): GithubDao {
        return appDatabase.githubDao()
    }
}
