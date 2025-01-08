package com.an.github.data.local.converter

import android.arch.persistence.room.TypeConverter
import com.an.github.AppConstants
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone



object TimestampConverter {

    private val df = SimpleDateFormat(AppConstants.DATE_TIME_FORMAT)

    @TypeConverter
    fun fromTimestamp(value: String): Date? {
        try {
            val timeZone = TimeZone.getTimeZone("IST")
            df.timeZone = timeZone
            return df.parse(value)

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }


    @TypeConverter
    fun dateToTimestamp(value: Date): String {
        val timeZone = TimeZone.getTimeZone("IST")
        df.timeZone = timeZone
        return df.format(value)
    }
}