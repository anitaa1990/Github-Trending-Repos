package com.an.github.data.local.entity

import com.google.gson.annotations.SerializedName

//@Entity
class GithubEntity(
                   val id: Long,

                   var page: Long,

                   var totalPages: Long,

                   var name: String,

                   @SerializedName("full_name")
                   var fullName: String,

//                   @Embedded
                   var owner: Owner,

                   @SerializedName("html_url")
                   var htmlUrl: String,

                   var description: String,

                   @SerializedName("contributors_url")
                   var contributorsUrl: String,

//                   @TypeConverters(TimestampConverter::class)
                   @SerializedName("created_at")
                   var createdAt: String,

                   @SerializedName("stargazers_count")
                   var starsCount: Long,

                   var watchers: Long,

                   var forks: Long,

                   var language: String?) {


    fun isLastPage() : Boolean {
        return page >= totalPages
    }
}
