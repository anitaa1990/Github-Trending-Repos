package com.an.github.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.webkit.WebSettings;

import com.an.github.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.an.github.AppConstants.DATE_TIME_FORMAT;
import static com.an.github.AppConstants.LANGUAGE_COLOR_MAP;

public class AppUtils {

    public static String getDate(String dateString) {

        try{
            SimpleDateFormat format1 = new SimpleDateFormat(DATE_TIME_FORMAT);
            Date date = format1.parse(dateString);
            DateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
            return sdf.format(date);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return "xx";
        }
    }

    public static String getTime(String dateString) {

        try{
            SimpleDateFormat format1 = new SimpleDateFormat(DATE_TIME_FORMAT);
            Date date = format1.parse(dateString);
            DateFormat sdf = new SimpleDateFormat("h:mm a");
            Date netDate = (date);
            return sdf.format(netDate);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return "xx";
        }
    }

    public static int getWebViewCacheMode(Context context) {
        return isConnected(context) ? WebSettings.LOAD_DEFAULT : WebSettings.LOAD_CACHE_ELSE_NETWORK;
    }

    public static boolean isConnected(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo connection = manager.getActiveNetworkInfo();
        if (connection != null && connection.isConnectedOrConnecting()){
            return true;
        }
        return false;
    }


    public static Pair[] getTransitionElements(Context context,
                                               View imageView,
                                               View titleView,
                                               View revealView,
                                               View languageView) {
        List<Pair<View, String>> pairs = new ArrayList<>();
        pairs.add(new Pair(imageView, context.getString(R.string.transition_image)));
        pairs.add(new Pair(titleView, context.getString(R.string.transition_title)));
        if(revealView.getVisibility() == View.VISIBLE) new Pair(revealView, context.getString(R.string.transition_background));
        if(languageView.getVisibility() == View.VISIBLE) new Pair(languageView, context.getString(R.string.transition_language));

        Pair[] pairArr = new Pair[pairs.size()];
        pairArr = pairs.toArray(pairArr);
        return pairArr;
    }

    public static int getColorByLanguage(Context context,
                                         String language) {
        if(LANGUAGE_COLOR_MAP.containsKey(language))
            return ContextCompat.getColor(context, LANGUAGE_COLOR_MAP.get(language));
        else return ContextCompat.getColor(context, R.color.colorPrimary);
    }

    public static void updateStatusBarColor(Activity activity,
                                            int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(color);
        }
    }


    public static int lighten(int color, double fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        red = lightenColor(red, fraction);
        green = lightenColor(green, fraction);
        blue = lightenColor(blue, fraction);
        int alpha = Color.alpha(color);
        return Color.argb(alpha, red, green, blue);
    }

    private static int lightenColor(int color, double fraction) {
        return (int) Math.min(color + (color * fraction), 255);
    }

    public static Drawable updateGradientDrawableColor(Context context,
                                                       int bgColor) {
        GradientDrawable drawable = (GradientDrawable) context.getResources().getDrawable(R.drawable.ic_circle);
        drawable.setColor(bgColor);
        return drawable;
    }

    public static Drawable updateStateListDrawableColor(Drawable stateListDrawable,
                                                        int bgColor) {
        DrawableContainer.DrawableContainerState drawableContainerState = (DrawableContainer.DrawableContainerState) stateListDrawable.getConstantState();
        Drawable[] children = drawableContainerState.getChildren();
        GradientDrawable selectedDrawable = (GradientDrawable) children[0];
        GradientDrawable unselectedDrawable = (GradientDrawable) children[1];
        selectedDrawable.setColor(AppUtils.lighten(bgColor, 0.1));
        unselectedDrawable.setColor(bgColor);
        return stateListDrawable;
    }

    public static Drawable updateStateListDrawableStrokeColor(Drawable stateListDrawable,
                                                              int bgColor) {
        DrawableContainer.DrawableContainerState drawableContainerState = (DrawableContainer.DrawableContainerState) stateListDrawable.getConstantState();
        Drawable[] children = drawableContainerState.getChildren();
        GradientDrawable selectedDrawable = (GradientDrawable) children[0];
        GradientDrawable unselectedDrawable = (GradientDrawable) children[1];
        selectedDrawable.setColor(AppUtils.lighten(bgColor, 0.1));
        unselectedDrawable.setStroke(1, bgColor);
        return stateListDrawable;
    }
}
