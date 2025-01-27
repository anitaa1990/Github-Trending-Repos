package com.an.github.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.an.github.data.local.entity.GithubRemoteKey

@Dao
interface GithubRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<GithubRemoteKey>)

    @Query("Select * From github_remote_key Where github_id = :id")
    suspend fun getRemoteKeyByGithubId(id: Long): GithubRemoteKey?

    @Query("Delete From github_remote_key")
    suspend fun clearRemoteKeys()

    @Query("Select created_at From github_remote_key Order By created_at DESC LIMIT 1")
    suspend fun getCreationTime(): Long?
}
