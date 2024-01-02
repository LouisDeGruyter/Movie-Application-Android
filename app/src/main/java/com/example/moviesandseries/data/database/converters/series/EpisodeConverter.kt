package com.example.moviesandseries.data.database.converters.series

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.series.DbEpisode
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EpisodeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDbEpisode(dbEpisode: DbEpisode): String {
        return gson.toJson(dbEpisode)
    }

    @TypeConverter
    fun toDbEpisode(json: String): DbEpisode {
        return gson.fromJson(json, DbEpisode::class.java)
    }

    @TypeConverter
    fun fromDbEpisodeList(dbEpisodeList: List<DbEpisode>): String {
        return gson.toJson(dbEpisodeList)
    }

    @TypeConverter
    fun toDbEpisodeList(json: String): List<DbEpisode> {
        val listType = object : TypeToken<List<DbEpisode>>() {}.type
        return gson.fromJson(json, listType)
    }
}
