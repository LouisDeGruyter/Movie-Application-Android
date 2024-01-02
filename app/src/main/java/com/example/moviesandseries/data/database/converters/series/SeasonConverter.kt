package com.example.moviesandseries.data.database.converters.series

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.series.DbSeason
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for converting DbSeason objects to and from JSON strings.
 */
class SeasonConverter {
    // Create a Gson instance for JSON serialization and deserialization
    private val gson = Gson()

    /**
     * Converts a list of DbSeason objects to its JSON representation.
     *
     * @param dbSeasonList The list of DbSeason objects to convert.
     * @return A JSON string representing the list of DbSeason objects.
     */
    @TypeConverter
    fun fromDbSeasonList(dbSeasonList: List<DbSeason>): String {
        return gson.toJson(dbSeasonList)
    }

    /**
     * Converts a JSON string to a list of DbSeason objects.
     *
     * @param json The JSON string representing the list of DbSeason objects.
     * @return The deserialized list of DbSeason objects.
     */
    @TypeConverter
    fun toDbSeasonList(json: String): List<DbSeason> {
        // Define the type of the list using TypeToken
        val listType = object : TypeToken<List<DbSeason>>() {}.type
        // Deserialize the JSON string to a list of DbSeason objects
        return gson.fromJson(json, listType)
    }
}
