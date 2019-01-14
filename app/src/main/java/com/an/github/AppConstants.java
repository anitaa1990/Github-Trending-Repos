package com.an.github;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface AppConstants {

    String QUERY_SORT = "stars";
    String QUERY_ORDER = "desc";
    String QUERY_API = "android";

    String PAGE_MAX_SIZE = "20";
    String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    String INTENT_POST = "intent_post";

    Map<String, Integer> LANGUAGE_COLOR_MAP = Collections.unmodifiableMap(
            new HashMap<String, Integer>() {{
                put("Java", R.color.colorPrimary);
                put("Kotlin", R.color.color_orange);
                put("Dart", R.color.color_blue);
                put("JavaScript", R.color.color_yellow);
                put("CSS", R.color.color_yellow);
            }});
}
