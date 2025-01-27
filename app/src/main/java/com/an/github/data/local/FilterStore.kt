package com.an.github.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.an.github.data.remote.model.GithubFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FilterStore(
    private val context: Context
) {
    // Create some keys we will use them to store and retrieve the data
    companion object {
        val GITHUB_FILTER_KEY = stringPreferencesKey("github_filter")

        // Create the dataStore and give it a name same as shared preferences
        private val Context.dataStore by preferencesDataStore("filter_preferences")
    }
    suspend fun storeGithubFilter(filter: GithubFilter) {
        context.dataStore.edit {
            it[GITHUB_FILTER_KEY] = filter.name
        }
    }

    val githubFilter: Flow<GithubFilter?> = context.dataStore.data.map { prefs ->
        prefs[GITHUB_FILTER_KEY]?.let { GithubFilter.valueOf(it) }
    }
}
