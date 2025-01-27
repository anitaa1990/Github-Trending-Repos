package com.an.github.data.remote.model

enum class GithubFilter {
    Android, Kotlin, Swift, JavaScript
}

fun GithubFilter.api() = this.name.lowercase()

fun GithubFilter.equalTo(filter: GithubFilter?) = this == filter
