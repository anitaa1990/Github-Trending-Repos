package com.an.github.ui.viewmodel;

import android.arch.lifecycle.ViewModel;
import com.an.github.data.SingleLiveEvent;
import com.an.github.data.local.dao.GithubDao;
import com.an.github.data.local.entity.GithubEntity;
import com.an.github.data.remote.api.GithubApiService;
import com.an.github.data.repository.GithubRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GithubListViewModel extends ViewModel {

    private Long currentPage = 0l;
    private GithubRepository repository;

    private List<GithubEntity> repositories = new ArrayList<>();
    private SingleLiveEvent<List<GithubEntity>> repoListLiveData = new SingleLiveEvent<>();

    @Inject
    public GithubListViewModel(GithubDao githubDao, GithubApiService githubApiService) {
        repository = new GithubRepository(githubDao, githubApiService);
    }

    public void fetchRepositories() {
        repository.getRepositories(++currentPage)
                .subscribe(resource -> {
                    if(resource.isLoaded()) {
                        repositories.addAll(resource.data);
                        getRepositoryListLiveData().postValue(resource.data);
                    }
                });
    }


    public List<GithubEntity> getRepositories() {
        return repositories;
    }

    public SingleLiveEvent<List<GithubEntity>> getRepositoryListLiveData() {
        return repoListLiveData;
    }

    public boolean isLastPage() {
        return getRepositoryListLiveData().getValue() != null &&
                !getRepositoryListLiveData().getValue().isEmpty() ?
                getRepositoryListLiveData().getValue().get(0).isLastPage() :
                false;
    }
}
