package com.example.moviesandseries.data.database.converters.series

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.series.DbEpisode
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for converting DbEpisode objects to and from JSON strings.
 */
class EpisodeConverter {
    // Create a Gson instance for JSON serialization and deserialization
    private val gson = Gson()

    /**
     * Converts a single DbEpisode object to its JSON representation.
     *
     * @param dbEpisode The DbEpisode object to convert.
     * @return A JSON string representing the DbEpisode object.
     */
    @TypeConverter
    fun fromDbEpisode(dbEpisode: DbEpisode): String {
        return gson.toJson(dbEpisode)
    }

    /**
     * Converts a JSON string to a DbEpisode object.
     *
     * @param json The JSON string representing the DbEpisode object.
     * @return The deserialized DbEpisode object.
     */
    @TypeConverter
    fun toDbEpisode(json: String): DbEpisode {
        return gson.fromJson(json, DbEpisode::class.java)
    }

}
