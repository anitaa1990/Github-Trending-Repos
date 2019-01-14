package com.an.github.utils;

import android.app.Activity;
import android.support.v4.app.ShareCompat;

public class ShareUtils {

    public static void shareUrl(Activity activity,
                                String url) {
        ShareCompat.IntentBuilder
                .from(activity)
                .setType("text/plain")
                .setChooserTitle("Share URL")
                .setText(url)
                .startChooser();
    }
}
