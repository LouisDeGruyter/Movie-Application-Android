package com.example.moviesandseries.data.database.converters.movie

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.movies.DbMovie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for converting DbMovie objects to and from JSON strings.
 */
class MovieConverter {
    // Create a Gson instance for JSON serialization and deserialization
    private val gson = Gson()

}
