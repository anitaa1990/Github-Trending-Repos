package com.an.github.db;

import android.support.test.runner.AndroidJUnit4;

import com.an.github.data.local.entity.GithubEntity;
import com.an.github.util.MockTestUtil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class GithubDbTest extends DbTest {

    @Test
    public void insertAndReadPostsTest() {
        List<GithubEntity> repositories = MockTestUtil.mockRepositories();
        db.githubDao().insertRepositories(repositories);

        List<GithubEntity> storedPosts1 = db.githubDao().getRepositoriesByPage(1l);
        Assert.assertEquals(2l, storedPosts1.size());
        Assert.assertEquals(new Long(1), storedPosts1.get(0).getId());

        List<GithubEntity> storedPosts2 = db.githubDao().getRepositoriesByPage(2l);
        Assert.assertEquals(1l, storedPosts2.size());
        Assert.assertEquals(new Long(3), storedPosts2.get(0).getId());
    }


    @Test
    public void emptyPostsTest() {
        List<GithubEntity> repositories = MockTestUtil.mockRepositories();
        db.githubDao().insertRepositories(repositories);

        List<GithubEntity> storedPosts = db.githubDao().getRepositoriesByPage(3l);
        Assert.assertEquals(0, storedPosts.size());
    }
}
