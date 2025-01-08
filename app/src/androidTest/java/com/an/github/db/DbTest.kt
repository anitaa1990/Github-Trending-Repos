package com.an.github.db

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.an.github.data.local.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
abstract class DbTest {

    protected lateinit var db: AppDatabase

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDatabase::class.java).build()
    }

    @After
    fun closeDb() {
        db.close()
    }
}