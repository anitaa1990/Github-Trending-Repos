package com.an.github.utils

import android.app.Activity
import android.support.v4.app.ShareCompat

object ShareUtils {

    fun shareUrl(activity: Activity,
                 url: String) {
        ShareCompat.IntentBuilder
                .from(activity)
                .setType("text/plain")
                .setChooserTitle("Share URL")
                .setText(url)
                .startChooser()
    }
}
