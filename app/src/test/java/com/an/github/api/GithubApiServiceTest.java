package com.an.github.api;

import com.an.github.data.remote.api.GithubApiService;
import com.an.github.data.remote.model.GithubApiResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Response;

public class GithubApiServiceTest extends ApiAbstract<GithubApiService> {

    private GithubApiService service;

    @Before
    public void initService() {
        this.service = createService(GithubApiService.class);
    }

    @Test
    public void fetchPostsTest() throws IOException {
        enqueueResponse("test_repositories.json");
        Response<GithubApiResponse> response = service.fetchRepositories("stars", "desc", 1l).blockingFirst();
        Assert.assertEquals(true, response.isSuccessful());

        GithubApiResponse apiResponse = response.body();
        Assert.assertEquals(new Long(806201), apiResponse.getTotalCount());
        Assert.assertEquals(10, apiResponse.getItems().size());
    }
}
