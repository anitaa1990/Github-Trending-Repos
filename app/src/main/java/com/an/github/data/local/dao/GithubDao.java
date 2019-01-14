package com.an.github.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.an.github.data.local.entity.GithubEntity;

import java.util.List;

@Dao
public interface GithubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertRepositories(List<GithubEntity> githubEntities);

    @Query("SELECT * FROM `GithubEntity` where page = :page")
    List<GithubEntity> getRepositoriesByPage(Long page);
}
