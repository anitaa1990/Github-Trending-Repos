package com.an.github.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.an.github.data.local.entity.GithubEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repositories: List<GithubEntity>)

    @Query("SELECT * FROM GithubEntity")
    fun pagingSource(): PagingSource<Int, GithubEntity>

    @Query("SELECT * FROM GithubEntity")
    fun getRepositories(): List<GithubEntity>

    @Query("SELECT * FROM GithubEntity WHERE remoteId =:remoteId")
    fun getRepository(remoteId: Long): Flow<GithubEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRepository(repository: GithubEntity)

    @Query("DELETE FROM GithubEntity")
    suspend fun clearAll()
}
