package com.an.github.ui.custom.recyclerview

import android.view.View

import com.an.github.data.local.entity.GithubEntity

interface RecyclerLayoutClickListener {

    fun redirectToDetailScreen(imageView: View,
                               titleView: View,
                               revealView: View,
                               languageView: View,
                               githubEntity: GithubEntity)

    fun sharePost(githubEntity: GithubEntity)
}
