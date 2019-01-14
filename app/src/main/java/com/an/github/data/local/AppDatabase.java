package com.an.github.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.an.github.data.local.converter.TimestampConverter;
import com.an.github.data.local.dao.GithubDao;
import com.an.github.data.local.entity.GithubEntity;


@Database(entities = {GithubEntity.class}, version = 1,  exportSchema = false)
@TypeConverters({TimestampConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract GithubDao githubDao();
}
