package com.example.moviesandseries.data.database.converters

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.DbGenre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDbGenre(dbGenre: DbGenre): String {
        return gson.toJson(dbGenre)
    }

    @TypeConverter
    fun toDbGenre(json: String): DbGenre {
        return gson.fromJson(json, DbGenre::class.java)
    }

    @TypeConverter
    fun fromDbGenreList(dbGenreList: List<DbGenre>): String {
        return gson.toJson(dbGenreList)
    }

    @TypeConverter
    fun toDbGenreList(json: String): List<DbGenre> {
        val listType = object : TypeToken<List<DbGenre>>() {}.type
        return gson.fromJson(json, listType)
    }
}
