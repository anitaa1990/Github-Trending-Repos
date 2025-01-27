package com.an.github.data.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.an.github.data.local.AppDatabase
import com.an.github.data.local.dao.GithubDao
import com.an.github.data.local.entity.GithubEntity
import com.an.github.data.remote.api.GithubApiService
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class GithubRepositoryTest: TestCase() {
    private lateinit var db: AppDatabase
    private val apiService: GithubApiService = mock()

    private lateinit var repository: GithubRepository

    private val expectedRepositories = getRepositories()

    // Override function setUp() and annotate it with @Before.
    // The @Before annotation makes sure fun setupDatabase() is executed before each class.
    // The function then creates a database using Room.inMemoryDatabaseBuilder which creates
    // a database in RAM instead of the persistence storage. This means the database will be
    // cleared once the process is killed.
    @Before
    public override fun setUp() {
        // get context -- since this is an instrumental test it requires
        // context from the running application
        // initialize the db and dao variable
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        val dao: GithubDao = db.githubDao
        repository = GithubRepository(dao, apiService)
    }

    // Override function closeDb() and annotate it with @After.
    // @After annotation means our closing function will be called every-time after
    // executing test cases. This function will be called at last when this test class is called.
    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun getRepositoriesWhenNoneExistsInDb() = runBlocking {
        // When there are no records in the db, when we fetch repositories by filters,
        // the list is empty and does not cause errors
        val actualRepositories = repository.getRepositories()
        assertEquals(0, actualRepositories.size)
    }

    @Test
    fun insertRepositoriesIfNoRecordsExistsInDb() = runBlocking {
        repository.addRepositories(expectedRepositories)

        val repositories = repository.getRepositories()
        assertEquals(expectedRepositories.size, repositories.size)

        for(index in 0 until repositories.size) {
            assertEquals(expectedRepositories[index].name, repositories[index].name)
            assertEquals(expectedRepositories[index].remoteId, repositories[index].remoteId)
        }
    }

    @Test
    fun deleteAllRepositoriesAndVerifyRecordsAreDeleted() = runBlocking {
        repository.addRepositories(expectedRepositories)

        // delete movies from db
        repository.deleteAll()

        // verify all records are deleted
        val dbRepositories = repository.getRepositories()
        assertEquals(0, dbRepositories.size)
    }

    private fun getRepositories(): List<GithubEntity> {
        return listOf<GithubEntity>(
            GithubEntity(
                remoteId = 12,
                name = "flutter",
                fullName = "",
                avatarUrl = "",
                htmlUrl = "",
                description = "",
                contributorsUrl = "",
                createdAt = "",
                starsCount = 0L,
                language = "",
                forks = 0L,
                watchers = 0L
            ),
            GithubEntity(
                remoteId = 14,
                name = "free-programming-books-zh_CN",
                fullName = "",
                avatarUrl = "",
                htmlUrl = "",
                description = "",
                contributorsUrl = "",
                createdAt = "",
                starsCount = 0L,
                language = "",
                forks = 0L,
                watchers = 0L
            ),
            GithubEntity(
                remoteId = 15,
                name = "material-design-icons",
                fullName = "",
                avatarUrl = "",
                htmlUrl = "",
                description = "",
                contributorsUrl = "",
                createdAt = "",
                starsCount = 0L,
                language = "",
                forks = 0L,
                watchers = 0L
            )
        )
    }
}
