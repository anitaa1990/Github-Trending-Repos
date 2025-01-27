package com.an.github.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("github_remote_key")
data class GithubRemoteKey(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "github_id")
    val githubId: Long,
    val prevKey: Int?,
    val currentPage: Int,
    val nextKey: Int?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)
