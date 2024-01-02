package com.example.moviesandseries.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for converting Int values to and from JSON strings.
 */
class IntConverter {
    // Create a Gson instance for JSON serialization and deserialization
    private val gson = Gson()

    /**
     * Converts a list of Int values to its JSON representation.
     *
     * @param intList The list of Int values to convert.
     * @return A JSON string representing the list of Int values.
     */
    @TypeConverter
    fun fromIntList(intList: List<Int>): String {
        return gson.toJson(intList)
    }

    /**
     * Converts a JSON string to a list of Int values.
     *
     * @param json The JSON string representing the list of Int values.
     * @return The deserialized list of Int values.
     */
    @TypeConverter
    fun toIntList(json: String): List<Int> {
        // Define the type of the list using TypeToken
        val listType = object : TypeToken<List<Int>>() {}.type
        // Deserialize the JSON string to a list of Int values
        return gson.fromJson(json, listType)
    }
}
