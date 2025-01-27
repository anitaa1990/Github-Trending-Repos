package com.an.github.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GithubEntity(
    @PrimaryKey(autoGenerate = true)
    val localId: Long = 0,

    @SerializedName("id")
    val remoteId: Long,

    val name: String,

    @SerializedName("full_name")
    val fullName: String,

    val owner: Owner,

    @SerializedName("html_url")
    val htmlUrl: String,
    val description: String,

    @SerializedName("contributors_url")
    val contributorsUrl: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("stargazers_count")
    val starsCount: Long,
    val language: String,
    val forks: Long,
    val watchers: Long
)
