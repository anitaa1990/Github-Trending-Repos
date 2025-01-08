package com.an.github.repository

import com.an.github.data.Resource
import com.an.github.data.local.dao.GithubDao
import com.an.github.data.local.entity.GithubEntity
import com.an.github.data.remote.api.GithubApiService
import com.an.github.data.remote.model.GithubApiResponse
import com.an.github.data.repository.GithubRepository
import com.an.github.util.MockTestUtil

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import retrofit2.Response

//@RunWith(MockitoJUnitRunner::class)
class GithubRepositoryTest {

//    @Mock
//    internal lateinit var githubDao: GithubDao
//
//    @Mock
//    internal lateinit var githubApiService: GithubApiService
//
//    private lateinit var repository: GithubRepository
//
//    @get:Rule
//    public var instantExecutorRule = InstantTaskExecutorRule()
//
//    @Before
//    fun init() {
//        repository = GithubRepository(githubDao, githubApiService)
//    }
//
//    @Test
//    fun loadPostsTest() {
//        val page = 1L
//
//        val loadFromDB = MockTestUtil.mockRepositories()
//        `when`(githubDao.getRepositoriesByPage(page))
//                .thenReturn(loadFromDB)
//
//        `when`<Observable<Response<GithubApiResponse>>>(githubApiService.fetchRepositories(QUERY_SORT, QUERY_ORDER, page))
//                .thenReturn(Observable.empty<Response<GithubApiResponse>>())
//
//        val data = repository.getRepositories(page)
//        verify<GithubDao>(githubDao).getRepositoriesByPage(page)
//        verify<GithubApiService>(githubApiService).fetchRepositories(QUERY_SORT, QUERY_ORDER, page)
//
//        val testObserver = TestObserver<Resource<List<GithubEntity>>>()
//        data.subscribe(testObserver)
//        testObserver.assertNoErrors()
//    }
}
