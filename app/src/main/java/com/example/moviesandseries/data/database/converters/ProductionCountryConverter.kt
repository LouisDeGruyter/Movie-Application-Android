package com.example.moviesandseries.data.database.converters

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.DbProductionCountry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for converting DbProductionCountry objects to and from JSON strings.
 */
class ProductionCountryConverter {
    // Create a Gson instance for JSON serialization and deserialization
    private val gson = Gson()

    /**
     * Converts a list of DbProductionCountry objects to its JSON representation.
     *
     * @param dbProductionCountryList The list of DbProductionCountry objects to convert.
     * @return A JSON string representing the list of DbProductionCountry objects.
     */
    @TypeConverter
    fun fromDbProductionCountryList(dbProductionCountryList: List<DbProductionCountry>): String {
        return gson.toJson(dbProductionCountryList)
    }

    /**
     * Converts a JSON string to a list of DbProductionCountry objects.
     *
     * @param json The JSON string representing the list of DbProductionCountry objects.
     * @return The deserialized list of DbProductionCountry objects.
     */
    @TypeConverter
    fun toDbProductionCountryList(json: String): List<DbProductionCountry> {
        // Define the type of the list using TypeToken
        val listType = object : TypeToken<List<DbProductionCountry>>() {}.type
        // Deserialize the JSON string to a list of DbProductionCountry objects
        return gson.fromJson(json, listType)
    }
}
