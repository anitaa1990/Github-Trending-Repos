package com.an.github.ui.viewmodel

import android.arch.lifecycle.ViewModel
import com.an.github.data.SingleLiveEvent
import com.an.github.data.local.dao.GithubDao
import com.an.github.data.local.entity.GithubEntity
import com.an.github.data.remote.api.GithubApiService
import com.an.github.data.repository.GithubRepository

import java.util.ArrayList

import javax.inject.Inject

class GithubListViewModel @Inject
constructor(githubDao: GithubDao, githubApiService: GithubApiService) : ViewModel() {

    private var currentPage: Long = 0L
    private val repository: GithubRepository = GithubRepository(githubDao, githubApiService)

    private val repositories = ArrayList<GithubEntity>()
    private val repositoryListLiveData = SingleLiveEvent<List<GithubEntity>>()


    fun fetchRepositories() {
        repository.getRepositories((++currentPage))
                .subscribe { resource ->
                    if (resource.isLoaded) {
                        resource.data?.let { repositories.addAll(it) }
                        repositoryListLiveData.postValue(resource.data)
                    }
                }
    }

    fun isLastPage(): Boolean {
        return repositoryListLiveData.value?.let { it[0].isLastPage() }
        ?: run {
            false
        }
    }

    fun getRepositoryLiveData() = repositoryListLiveData

    fun getRepositories(): List<GithubEntity> {
        return repositories
    }
}
