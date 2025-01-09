package com.an.github.data.remote.model

import com.an.github.data.local.entity.GithubEntity
import com.google.gson.annotations.SerializedName

data class GithubApiResponse(
    @SerializedName("total_count")
    val totalCount: Long,
    val items: List<GithubEntity>
)
