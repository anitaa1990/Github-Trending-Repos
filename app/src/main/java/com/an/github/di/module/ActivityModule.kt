package com.an.github.di.module

import com.an.github.ui.activity.GithubListActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeGithubListActivity(): GithubListActivity
}