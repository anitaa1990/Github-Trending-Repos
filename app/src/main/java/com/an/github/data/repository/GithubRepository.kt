package com.an.github.data.repository

import com.an.github.data.NetworkBoundResource
import com.an.github.data.Resource
import com.an.github.data.local.dao.GithubDao
import com.an.github.data.local.entity.GithubEntity
import com.an.github.data.remote.api.GithubApiService
import com.an.github.data.remote.model.GithubApiResponse

import javax.inject.Singleton


@Singleton
class GithubRepository(private val githubDao: GithubDao, private val githubApiService: GithubApiService) {

//    fun getRepositories(page: Long): Observable<Resource<List<GithubEntity>>> {
//        return object : NetworkBoundResource<List<GithubEntity>, GithubApiResponse>() {
//
//            override fun saveCallResult(item: GithubApiResponse) {
//                val repositories = item.items
//                for (githubEntity in repositories) {
//                    githubEntity.page = page
//                    githubEntity.totalPages = item.totalCount
//                }
//                githubDao.insertRepositories(repositories)
//            }
//
//            override fun shouldFetch(): Boolean {
//                return true
//            }
//
//            override fun loadFromDb(): Flowable<List<GithubEntity>> {
//                val repositories = githubDao.getRepositoriesByPage(page)
//                return Flowable.just(repositories)
//            }
//
//            override fun createCall(): Observable<Resource<GithubApiResponse>> {
//                return githubApiService.fetchRepositories(QUERY_SORT, QUERY_ORDER, page)
//                        .flatMap<Resource<GithubApiResponse>> { response ->
//                            Observable.just(if (response.isSuccessful)
//                                Resource.success(response.body()!!)
//                            else
//                                Resource.error("", GithubApiResponse(0, emptyList())))
//                        }
//            }
//
//        }.getAsObservable()
//    }
}
