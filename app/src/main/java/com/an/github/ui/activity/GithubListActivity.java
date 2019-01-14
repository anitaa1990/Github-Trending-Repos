package com.an.github.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.an.github.R;
import com.an.github.data.local.entity.GithubEntity;
import com.an.github.databinding.GithubListActivityBinding;
import com.an.github.factory.ViewModelFactory;
import com.an.github.ui.adapter.FilterListAdapter;
import com.an.github.ui.adapter.GithubListAdapter;
import com.an.github.ui.custom.recyclerview.RecyclerItemClickListener;
import com.an.github.ui.custom.recyclerview.RecyclerLayoutClickListener;
import com.an.github.ui.custom.recyclerview.RecyclerViewPaginator;
import com.an.github.ui.viewmodel.GithubListViewModel;
import com.an.github.utils.AnimUtils;
import com.an.github.utils.AppUtils;
import com.an.github.utils.NavigatorUtils;
import com.an.github.utils.ShareUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class GithubListActivity extends AppCompatActivity implements RecyclerLayoutClickListener {

    @Inject
    ViewModelFactory viewModelFactory;

    private GithubListActivityBinding binding;
    private GithubListViewModel githubListViewModel;

    private GithubListAdapter githubListAdapter;
    private FilterListAdapter filterListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        initialiseViewModel();
        initialiseView();
    }

    private void initialiseView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_list);
        setSupportActionBar(binding.mainToolbar.toolbar);

        filterListAdapter = new FilterListAdapter(Arrays.asList(getResources().getStringArray(R.array.list_filters)));
        binding.filterList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.filterList.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), (parentView, childView, position) -> {
            filterListAdapter.updateSelection(position);
            githubListAdapter.getFilter().filter(filterListAdapter.getItem(position));
        }));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        githubListAdapter = new GithubListAdapter(getApplicationContext(), this);
        binding.recyclerView.setAdapter(githubListAdapter);
        binding.recyclerView.addOnScrollListener(new RecyclerViewPaginator(binding.recyclerView) {
            @Override
            public boolean isLastPage() {
                return githubListViewModel.isLastPage();
            }

            @Override
            public void loadMore() {
                githubListViewModel.fetchRepositories();
            }
        });

        /* This is to handle configuration changes:
         * during configuration change, when the activity
         * is recreated, we check if the viewModel
         * contains the list data. If so, there is no
         * need to call the api or load data from cache again */
        if(githubListViewModel.getRepositories().isEmpty()) {
            displayLoader();
            githubListViewModel.fetchRepositories();
        }
        else animateView(githubListViewModel.getRepositories());
    }


    private void initialiseViewModel() {
        githubListViewModel = ViewModelProviders.of(this, viewModelFactory).get(GithubListViewModel.class);
        githubListViewModel.getRepositoryListLiveData().observe(this, repositories -> {
            if(githubListAdapter.getItemCount() == 0) {
                if(!repositories.isEmpty()) {
                    animateView(repositories);

                } else displayEmptyView();

            } else if(!repositories.isEmpty()) displayDataView(repositories);
        });
    }

    private void displayLoader() {
        binding.viewLoader.rootView.setVisibility(View.VISIBLE);
    }

    private void hideLoader() {
        binding.viewLoader.rootView.setVisibility(View.GONE);
    }


    private void animateView(List<GithubEntity> repositories) {
        hideLoader();
        AnimUtils.slideView(binding.filterLayout, binding.filterList, filterListAdapter);
        displayDataView(repositories);
        binding.recyclerView.scheduleLayoutAnimation();
    }

    private void displayDataView(List<GithubEntity> repositories) {
        binding.viewEmpty.emptyContainer.setVisibility(View.GONE);
        githubListAdapter.setItems(repositories);
    }

    private void displayEmptyView() {
        hideLoader();
        binding.viewEmpty.emptyContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void redirectToDetailScreen(View imageView, View titleView, View revealView, View languageView, GithubEntity githubEntity) {
        NavigatorUtils.redirectToDetailScreen(this, githubEntity,
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, AppUtils.getTransitionElements(
                        getApplicationContext(), imageView, titleView, revealView, languageView
                )));
    }

    @Override
    public void sharePost(GithubEntity githubEntity) {
        ShareUtils.shareUrl(this, githubEntity.getHtmlUrl());
    }
}
