package com.an.github.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.an.github.data.local.entity.GithubEntity
import com.an.github.factory.ViewModelFactory
import com.an.github.ui.adapter.FilterListAdapter
import com.an.github.ui.adapter.GithubListAdapter
import com.an.github.ui.custom.recyclerview.RecyclerLayoutClickListener
import com.an.github.ui.viewmodel.GithubListViewModel
import javax.inject.Inject

class GithubListActivity : AppCompatActivity(), RecyclerLayoutClickListener {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

//    private lateinit var binding: GithubListActivityBinding
    private lateinit var githubListViewModel: GithubListViewModel

    private lateinit var githubListAdapter: GithubListAdapter
    private lateinit var filterListAdapter: FilterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        AndroidInjection.inject(this)
        initialiseViewModel()
        initialiseView()
    }

    private fun initialiseView() {
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_list)
//        setSupportActionBar(binding.mainToolbar.toolbar)
//
//        filterListAdapter = FilterListAdapter(Arrays.asList(*resources.getStringArray(R.array.list_filters)))
//        binding.filterList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
//        binding.filterList.addOnItemTouchListener(RecyclerItemClickListener(applicationContext,
//                object : RecyclerItemClickListener.OnRecyclerViewItemClickListener {
//                    override fun onItemClick(parentView: View, childView: View, position: Int) {
//                        filterListAdapter.updateSelection(position)
//                        githubListAdapter.filter.filter(filterListAdapter.getItem(position))
//                    }
//
//                }))
//
//        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
//        githubListAdapter = GithubListAdapter(applicationContext, this)
//        binding.recyclerView.adapter = githubListAdapter
//        binding.recyclerView.addOnScrollListener(object : RecyclerViewPaginator(binding.recyclerView) {
//            override val isLastPage: Boolean
//                get() = githubListViewModel.isLastPage()
//
//            override fun loadMore() {
//                githubListViewModel.fetchRepositories()
//            }
//        })
//
//        /* This is to handle configuration changes:
//         * during configuration change, when the activity
//         * is recreated, we check if the viewModel
//         * contains the list data. If so, there is no
//         * need to call the api or load data from cache again */
//        if (githubListViewModel.getRepositories().isEmpty()) {
//            displayLoader()
//            githubListViewModel.fetchRepositories()
//        } else
//            animateView(githubListViewModel.getRepositories())
    }


    private fun initialiseViewModel() {
//        githubListViewModel = ViewModelProviders.of(this, viewModelFactory).get(GithubListViewModel::class.java)
//        githubListViewModel.getRepositoryLiveData().observe(this, Observer { repositories ->
//            if (githubListAdapter.itemCount == 0) {
//                repositories.withNotNullNorEmpty {
//                    animateView(repositories)
//                } .otherwise {
//                    displayEmptyView()
//                }
//
//            } else {
//                repositories.withNotNullNorEmpty {
//                    displayDataView(repositories)
//                }
//            }
//        })
    }

    private fun displayLoader() {
//        binding.viewLoader.rootView.visibility = View.VISIBLE
    }

    private fun hideLoader() {
//        binding.viewLoader.rootView.visibility = View.GONE
    }


    private fun animateView(repositories: List<GithubEntity>?) {
//        hideLoader()
//        AnimUtils.slideView(binding.filterLayout, binding.filterList, filterListAdapter)
//        displayDataView(repositories)
//        binding.recyclerView.scheduleLayoutAnimation()
    }

    private fun displayDataView(repositories: List<GithubEntity>?) {
//        binding.viewEmpty.emptyContainer.visibility = View.GONE
//        githubListAdapter.setItems(repositories)
    }

    private fun displayEmptyView() {
        hideLoader()
//        binding.viewEmpty.emptyContainer.visibility = View.VISIBLE
    }

    override fun redirectToDetailScreen(imageView: View, titleView: View, revealView: View, languageView: View, githubEntity: GithubEntity) {
//        NavigatorUtils.redirectToDetailScreen(this, githubEntity,
//                ActivityOptionsCompat.makeSceneTransitionAnimation(this, *AppUtils.getTransitionElements(
//                        applicationContext, imageView, titleView, revealView, languageView
//                )))
    }

    override fun sharePost(githubEntity: GithubEntity) {
//        ShareUtils.shareUrl(this, githubEntity.htmlUrl)
    }
}
