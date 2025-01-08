package com.an.github.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.an.github.data.local.dao.GithubDao
import com.an.github.data.local.entity.GithubEntity

@Database(
    entities = [GithubEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val githubDao: GithubDao
}
