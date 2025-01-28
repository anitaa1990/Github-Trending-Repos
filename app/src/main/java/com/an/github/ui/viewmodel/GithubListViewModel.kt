package com.an.github.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.an.github.AppConstants.PAGE_SIZE
import com.an.github.AppConstants.PREFETCH_DISTANCE
import com.an.github.data.local.FilterStore
import com.an.github.data.remote.model.GithubFilter
import com.an.github.data.repository.GithubRepository
import com.an.github.data.source.GithubRemoteMediator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GithubListViewModel @Inject constructor(
    private val repository: GithubRepository,
    private val filterStore: FilterStore
) : ViewModel() {
    private val _selectedFilter = MutableStateFlow(GithubFilter.Android)

    @OptIn(ExperimentalCoroutinesApi::class)
    val repositories = _selectedFilter.flatMapLatest {
        getPager(it)
            .flow
            .cachedIn(viewModelScope)
    }

    val selectedFilter = _selectedFilter.asStateFlow()
    fun updateFilter(newFilter: GithubFilter) {
        _selectedFilter.update { newFilter }
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun getPager(filter: GithubFilter) = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            initialLoadSize = PAGE_SIZE, // How many items you want to load initially
        ),
        pagingSourceFactory = {
            // The pagingSourceFactory lambda should always return a brand new PagingSource
            // when invoked as PagingSource instances are not reusable.
            repository.getPagingSource()
        },
        remoteMediator = GithubRemoteMediator(
            filter = filter,
            filterStore = filterStore,
            repository = repository
        )
    )
}
