package com.an.github.di.module;

import com.an.github.ui.activity.GithubListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract GithubListActivity contributeGithubListActivity();
}