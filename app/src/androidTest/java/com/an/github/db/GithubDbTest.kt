package com.an.github.db
import com.an.github.util.MockTestUtil

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

//@RunWith(AndroidJUnit4::class)
class GithubDbTest : DbTest() {

//    @Test
//    fun insertAndReadPostsTest() {
//        val repositories = MockTestUtil.mockRepositories()
//        db.githubDao().insertRepositories(repositories)
//
//        val storedPosts1 = db.githubDao().getRepositoriesByPage(1L)
//        Assert.assertEquals(2L, storedPosts1.size.toLong())
//        Assert.assertEquals(1, storedPosts1[0].id)
//
//        val storedPosts2 = db.githubDao().getRepositoriesByPage(2L)
//        Assert.assertEquals(1L, storedPosts2.size.toLong())
//        Assert.assertEquals(3, storedPosts2[0].id)
//    }
//
//
//    @Test
//    fun emptyPostsTest() {
//        val repositories = MockTestUtil.mockRepositories()
//        db.githubDao().insertRepositories(repositories)
//
//        val storedPosts = db.githubDao().getRepositoriesByPage(3L)
//        Assert.assertEquals(0, storedPosts.size.toLong())
//    }
}
