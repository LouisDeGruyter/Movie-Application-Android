package com.example.moviesandseries.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IntConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromInt(intValue: Int): String {
        return gson.toJson(intValue)
    }

    @TypeConverter
    fun toInt(json: String): Int {
        return gson.fromJson(json, Int::class.java)
    }

    @TypeConverter
    fun fromIntList(intList: List<Int>): String {
        return gson.toJson(intList)
    }

    @TypeConverter
    fun toIntList(json: String): List<Int> {
        val listType = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(json, listType)
    }
}
