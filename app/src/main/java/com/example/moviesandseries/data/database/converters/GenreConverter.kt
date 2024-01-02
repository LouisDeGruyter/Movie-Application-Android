package com.example.moviesandseries.data.database.converters

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.DbGenre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for converting DbGenre objects to and from JSON strings.
 */
class GenreConverter {
    // Create a Gson instance for JSON serialization and deserialization
    private val gson = Gson()

    /**
     * Converts a list of DbGenre objects to its JSON representation.
     *
     * @param dbGenreList The list of DbGenre objects to convert.
     * @return A JSON string representing the list of DbGenre objects.
     */
    @TypeConverter
    fun fromDbGenreList(dbGenreList: List<DbGenre>): String {
        return gson.toJson(dbGenreList)
    }

    /**
     * Converts a JSON string to a list of DbGenre objects.
     *
     * @param json The JSON string representing the list of DbGenre objects.
     * @return The deserialized list of DbGenre objects.
     */
    @TypeConverter
    fun toDbGenreList(json: String): List<DbGenre> {
        // Define the type of the list using TypeToken
        val listType = object : TypeToken<List<DbGenre>>() {}.type
        // Deserialize the JSON string to a list of DbGenre objects
        return gson.fromJson(json, listType)
    }
}
