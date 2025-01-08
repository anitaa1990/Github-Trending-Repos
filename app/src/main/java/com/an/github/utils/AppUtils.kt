package com.an.github.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.DrawableContainer
import android.graphics.drawable.GradientDrawable
import android.net.ConnectivityManager
import android.os.Build
import android.view.View
import android.webkit.WebSettings

import com.an.github.R
import java.text.SimpleDateFormat
import java.util.ArrayList

object AppUtils {

//    fun getDate(dateString: String): String {
//
//        return try {
//            val format1 = SimpleDateFormat(DATE_TIME_FORMAT)
//            val date = format1.parse(dateString)
//            val sdf = SimpleDateFormat("MMM d, yyyy")
//            sdf.format(date)
//
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//            "xx"
//        }
//
//    }
//
//    fun getTime(dateString: String): String {
//
//        return try {
//            val format1 = SimpleDateFormat(DATE_TIME_FORMAT)
//            val date = format1.parse(dateString)
//            val sdf = SimpleDateFormat("h:mm a")
//            sdf.format(date)
//
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//            "xx"
//        }
//
//    }
//
//    fun getWebViewCacheMode(context: Context): Int {
//        return if (isConnected(context)) WebSettings.LOAD_DEFAULT else WebSettings.LOAD_CACHE_ELSE_NETWORK
//    }
//
//    private fun isConnected(context: Context): Boolean {
//        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val connection = manager.activeNetworkInfo
//        return connection != null && connection.isConnectedOrConnecting
//    }
//
//
//    fun getTransitionElements(context: Context,
//                              imageView: View,
//                              titleView: View,
//                              revealView: View,
//                              languageView: View): Array<Pair<View, String>> {
//
//        val pairs = ArrayList<Pair<View, String>>()
//        pairs.add(Pair(imageView, context.getString(R.string.transition_image)))
//        pairs.add(Pair(titleView, context.getString(R.string.transition_title)))
//        if (revealView.visibility == View.VISIBLE) Pair(revealView, context.getString(R.string.transition_background))
//        if (languageView.visibility == View.VISIBLE) Pair(languageView, context.getString(R.string.transition_language))
//
//        return pairs.toTypedArray()
//    }
//
//    fun getColorByLanguage(context: Context,
//                           language: String?): Int {
//
//        return LANGUAGE_COLOR_MAP[language]?.let {
//                        ContextCompat.getColor(context, it)
//                    } ?: run {
//                        ContextCompat.getColor(context, R.color.colorPrimary)
//                    }
//    }

    fun updateStatusBarColor(activity: Activity,
                             color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.statusBarColor = color
        }
    }


    private fun lighten(color: Int, fraction: Double): Int {
        var red = Color.red(color)
        var green = Color.green(color)
        var blue = Color.blue(color)
        red = lightenColor(red, fraction)
        green = lightenColor(green, fraction)
        blue = lightenColor(blue, fraction)
        val alpha = Color.alpha(color)
        return Color.argb(alpha, red, green, blue)
    }

    private fun lightenColor(color: Int, fraction: Double): Int {
        return Math.min(color + color * fraction, 255.0).toInt()
    }

    fun updateGradientDrawableColor(context: Context,
                                    bgColor: Int): Drawable {
        val drawable = context.resources.getDrawable(R.drawable.ic_circle) as GradientDrawable
        drawable.setColor(bgColor)
        return drawable
    }

    fun updateStateListDrawableColor(stateListDrawable: Drawable,
                                     bgColor: Int): Drawable {
        val drawableContainerState = stateListDrawable.constantState as DrawableContainer.DrawableContainerState
        val children = drawableContainerState.children
        val selectedDrawable = children[0] as GradientDrawable
        val unselectedDrawable = children[1] as GradientDrawable
        selectedDrawable.setColor(AppUtils.lighten(bgColor, 0.1))
        unselectedDrawable.setColor(bgColor)
        return stateListDrawable
    }

    fun updateStateListDrawableStrokeColor(stateListDrawable: Drawable,
                                           bgColor: Int): Drawable {
        val drawableContainerState = stateListDrawable.constantState as DrawableContainer.DrawableContainerState
        val children = drawableContainerState.children
        val selectedDrawable = children[0] as GradientDrawable
        val unselectedDrawable = children[1] as GradientDrawable
        selectedDrawable.setColor(AppUtils.lighten(bgColor, 0.1))
        unselectedDrawable.setStroke(1, bgColor)
        return stateListDrawable
    }
}
