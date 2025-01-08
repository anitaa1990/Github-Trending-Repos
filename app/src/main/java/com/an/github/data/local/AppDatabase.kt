package com.an.github.data.local

import com.an.github.data.local.converter.TimestampConverter
import com.an.github.data.local.dao.GithubDao
import com.an.github.data.local.entity.GithubEntity


//@Database(entities = [GithubEntity::class], version = 1, exportSchema = false)
//@TypeConverters(TimestampConverter::class)
abstract class AppDatabase {

    abstract fun githubDao(): GithubDao
}
