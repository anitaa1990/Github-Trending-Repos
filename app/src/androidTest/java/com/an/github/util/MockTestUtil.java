package com.an.github.util;

import com.an.github.data.local.entity.GithubEntity;
import com.an.github.data.remote.model.GithubApiResponse;
import java.util.ArrayList;
import java.util.List;

public class MockTestUtil {

    public static GithubApiResponse mockApiResponse() {
        GithubApiResponse apiResponse = new GithubApiResponse();
        apiResponse.setTotalCount(1l);
        apiResponse.setItems(mockRepositories());
        return apiResponse;
    }

    public static List<GithubEntity> mockRepositories() {
        List<GithubEntity> repositories = new ArrayList<>();

        GithubEntity repository1 = new GithubEntity();
        repository1.setId(1l);
        repository1.setPage(1l);
        repository1.setTotalPages(10l);
        repository1.setName("AndroidTest1");
        repositories.add(repository1);

        GithubEntity repository2 = new GithubEntity();
        repository2.setId(2l);
        repository2.setPage(1l);
        repository2.setTotalPages(10l);
        repository2.setName("AndroidTest2");
        repositories.add(repository2);

        GithubEntity repository3 = new GithubEntity();
        repository3.setId(3l);
        repository3.setPage(2l);
        repository3.setTotalPages(10l);
        repository3.setName("AndroidTest3");
        repositories.add(repository3);

        return repositories;
    }
}
