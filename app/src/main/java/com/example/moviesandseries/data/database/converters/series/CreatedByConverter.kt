package com.example.moviesandseries.data.database.converters.series

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.series.DbCreatedBy
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for converting DbCreatedBy objects to and from JSON strings.
 */
class CreatedByConverter {
    // Create a Gson instance for JSON serialization and deserialization
    private val gson = Gson()

    /**
     * Converts a list of DbCreatedBy objects to its JSON representation.
     *
     * @param dbCreatedByList The list of DbCreatedBy objects to convert.
     * @return A JSON string representing the list of DbCreatedBy objects.
     */
    @TypeConverter
    fun fromDbCreatedByList(dbCreatedByList: List<DbCreatedBy>): String {
        return gson.toJson(dbCreatedByList)
    }

    /**
     * Converts a JSON string to a list of DbCreatedBy objects.
     *
     * @param json The JSON string representing the list of DbCreatedBy objects.
     * @return The deserialized list of DbCreatedBy objects.
     */
    @TypeConverter
    fun toDbCreatedByList(json: String): List<DbCreatedBy> {
        // Define the type of the list using TypeToken
        val listType = object : TypeToken<List<DbCreatedBy>>() {}.type
        // Deserialize the JSON string to a list of DbCreatedBy objects
        return gson.fromJson(json, listType)
    }
}
