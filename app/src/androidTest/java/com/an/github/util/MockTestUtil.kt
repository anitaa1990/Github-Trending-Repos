package com.an.github.util

import com.an.github.data.local.entity.GithubEntity
import com.an.github.data.local.entity.Owner
import com.an.github.data.remote.model.GithubApiResponse
import java.util.ArrayList

object MockTestUtil {

    fun mockApiResponse(): GithubApiResponse {
        return GithubApiResponse(1, mockRepositories())
    }

    fun mockRepositories(): List<GithubEntity> {
        val repositories = ArrayList<GithubEntity>()

        val repository1 = GithubEntity(1, 1, 10, "AndroidTest1", "",
                Owner("", ""), "", "", "",
                "", 0, 0, 0, "")
        repositories.add(repository1)

        val repository2 = GithubEntity(2, 1, 10, "AndroidTest2", "",
                Owner("", ""), "", "", "",
                "", 0, 0, 0, "")
        repositories.add(repository2)

        val repository3 = GithubEntity(3, 2, 10, "AndroidTest3", "",
                Owner("", ""), "", "", "",
                "", 0, 0, 0, "")
        repositories.add(repository3)

        return repositories
    }
}
