package com.example.moviesandseries.data.database.converters

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.DbProductionCompany
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for converting DbProductionCompany objects to and from JSON strings.
 */
class ProductionCompanyConverter {
    // Create a Gson instance for JSON serialization and deserialization
    private val gson = Gson()

    /**
     * Converts a list of DbProductionCompany objects to its JSON representation.
     *
     * @param dbProductionCompanyList The list of DbProductionCompany objects to convert.
     * @return A JSON string representing the list of DbProductionCompany objects.
     */
    @TypeConverter
    fun fromDbProductionCompanyList(dbProductionCompanyList: List<DbProductionCompany>): String {
        return gson.toJson(dbProductionCompanyList)
    }

    /**
     * Converts a JSON string to a list of DbProductionCompany objects.
     *
     * @param json The JSON string representing the list of DbProductionCompany objects.
     * @return The deserialized list of DbProductionCompany objects.
     */
    @TypeConverter
    fun toDbProductionCompanyList(json: String): List<DbProductionCompany> {
        // Define the type of the list using TypeToken
        val listType = object : TypeToken<List<DbProductionCompany>>() {}.type
        // Deserialize the JSON string to a list of DbProductionCompany objects
        return gson.fromJson(json, listType)
    }
}
