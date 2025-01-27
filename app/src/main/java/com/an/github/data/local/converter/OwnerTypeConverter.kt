package com.an.github.data.local.converter

import androidx.room.TypeConverter
import com.an.github.data.local.entity.Owner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OwnerTypeConverter {
    @TypeConverter
    fun fromString(value: String): Owner {
        val listType = object : TypeToken<Owner>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: Owner): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
