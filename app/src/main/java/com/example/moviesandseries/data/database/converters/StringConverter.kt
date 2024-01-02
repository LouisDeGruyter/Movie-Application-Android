package com.example.moviesandseries.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for converting List<String> objects to and from JSON strings.
 */
class StringConverter {
    // Create a Gson instance for JSON serialization and deserialization
    private val gson = Gson()

    /**
     * Converts a list of strings to its JSON representation.
     *
     * @param stringList The list of strings to convert.
     * @return A JSON string representing the list of strings.
     */
    @TypeConverter
    fun fromStringList(stringList: List<String>): String {
        return gson.toJson(stringList)
    }

    /**
     * Converts a JSON string to a list of strings.
     *
     * @param json The JSON string representing the list of strings.
     * @return The deserialized list of strings.
     */
    @TypeConverter
    fun toStringList(json: String): List<String> {
        // Define the type of the list using TypeToken
        val listType = object : TypeToken<List<String>>() {}.type
        // Deserialize the JSON string to a list of strings
        return gson.fromJson(json, listType)
    }
}
