package com.example.moviesandseries.data.database.converters
import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.DbMovie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDbMovie(dbMovie: DbMovie): String {
        return gson.toJson(dbMovie)
    }

    @TypeConverter
    fun toDbMovie(json: String): DbMovie {
        return gson.fromJson(json, DbMovie::class.java)
    }

    @TypeConverter
    fun fromDbMovieList(dbMovieList: List<DbMovie>): String {
        return gson.toJson(dbMovieList)
    }

    @TypeConverter
    fun toDbMovieList(json: String): List<DbMovie> {
        val listType = object : TypeToken<List<DbMovie>>() {}.type
        return gson.fromJson(json, listType)
    }
}
