package com.an.github.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.an.github.data.local.converter.OwnerTypeConverter
import com.an.github.data.local.dao.GithubDao
import com.an.github.data.local.dao.GithubRemoteKeyDao
import com.an.github.data.local.entity.GithubEntity
import com.an.github.data.local.entity.GithubRemoteKey

@Database(
    entities = [GithubEntity::class, GithubRemoteKey::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    OwnerTypeConverter::class
)
abstract class AppDatabase: RoomDatabase() {
    abstract val githubDao: GithubDao
    abstract val githubKeyDao: GithubRemoteKeyDao
}
