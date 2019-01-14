package com.an.github.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.an.github.di.ViewModelKey;
import com.an.github.factory.ViewModelFactory;
import com.an.github.ui.viewmodel.GithubListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(GithubListViewModel.class)
    protected abstract ViewModel githubListViewModel(GithubListViewModel githubListViewModel);
}