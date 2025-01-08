package com.an.github.data.remote.model

import com.an.github.data.local.entity.GithubEntity
import com.google.gson.annotations.SerializedName


class GithubApiResponse(@SerializedName("total_count")
                        var totalCount: Long,
                        var items: List<GithubEntity>) {
}
