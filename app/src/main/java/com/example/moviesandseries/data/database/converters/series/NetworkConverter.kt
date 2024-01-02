package com.example.moviesandseries.data.database.converters.series

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.series.DbNetwork
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for converting DbNetwork objects to and from JSON strings.
 */
class NetworkConverter {
    // Create a Gson instance for JSON serialization and deserialization
    private val gson = Gson()

    /**
     * Converts a list of DbNetwork objects to its JSON representation.
     *
     * @param dbNetworkList The list of DbNetwork objects to convert.
     * @return A JSON string representing the list of DbNetwork objects.
     */
    @TypeConverter
    fun fromDbNetworkList(dbNetworkList: List<DbNetwork>): String {
        return gson.toJson(dbNetworkList)
    }

    /**
     * Converts a JSON string to a list of DbNetwork objects.
     *
     * @param json The JSON string representing the list of DbNetwork objects.
     * @return The deserialized list of DbNetwork objects.
     */
    @TypeConverter
    fun toDbNetworkList(json: String): List<DbNetwork> {
        // Define the type of the list using TypeToken
        val listType = object : TypeToken<List<DbNetwork>>() {}.type
        // Deserialize the JSON string to a list of DbNetwork objects
        return gson.fromJson(json, listType)
    }
}
