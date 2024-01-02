package com.example.moviesandseries.data.database.converters.series

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.series.DbCreatedBy
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class CreatedByConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDbCreatedBy(dbCreatedBy: DbCreatedBy): String {
        return gson.toJson(dbCreatedBy)
    }

    @TypeConverter
    fun toDbCreatedBy(json: String): DbCreatedBy {
        return gson.fromJson(json, DbCreatedBy::class.java)
    }

    @TypeConverter
    fun fromDbCreatedByList(dbCreatedByList: List<DbCreatedBy>): String {
        return gson.toJson(dbCreatedByList)
    }

    @TypeConverter
    fun toDbCreatedByList(json: String): List<DbCreatedBy> {
        val listType = object : TypeToken<List<DbCreatedBy>>() {}.type
        return gson.fromJson(json, listType)
    }
}
