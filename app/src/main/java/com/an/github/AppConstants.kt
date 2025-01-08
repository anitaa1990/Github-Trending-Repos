package com.an.github

import java.util.Collections
import java.util.HashMap

interface AppConstants {
    companion object {

        val QUERY_SORT = "stars"
        val QUERY_ORDER = "desc"
        val QUERY_API = "android"

        val PAGE_MAX_SIZE = "20"
        val DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"

        val INTENT_POST = "intent_post"

        val LANGUAGE_COLOR_MAP = Collections.unmodifiableMap(
                object : HashMap<String, Int>() {
                    init {
                        put("Java", R.color.colorPrimary)
                        put("Kotlin", R.color.color_orange)
                        put("Dart", R.color.color_blue)
                        put("JavaScript", R.color.color_yellow)
                        put("CSS", R.color.color_yellow)
                    }
                })
    }
}
