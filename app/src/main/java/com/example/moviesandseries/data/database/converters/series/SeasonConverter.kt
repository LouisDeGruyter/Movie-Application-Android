package com.example.moviesandseries.data.database.converters.series

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.series.DbSeason
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SeasonConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDbSeason(dbSeason: DbSeason): String {
        return gson.toJson(dbSeason)
    }

    @TypeConverter
    fun toDbSeason(json: String): DbSeason {
        return gson.fromJson(json, DbSeason::class.java)
    }

    @TypeConverter
    fun fromDbSeasonList(dbSeasonList: List<DbSeason>): String {
        return gson.toJson(dbSeasonList)
    }

    @TypeConverter
    fun toDbSeasonList(json: String): List<DbSeason> {
        val listType = object : TypeToken<List<DbSeason>>() {}.type
        return gson.fromJson(json, listType)
    }
}
