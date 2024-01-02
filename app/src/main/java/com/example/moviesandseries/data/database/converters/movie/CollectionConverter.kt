package com.example.moviesandseries.data.database.converters.movie

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.movies.DbCollection
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for converting DbCollection objects to and from JSON strings.
 */
class CollectionConverter {
    // Create a Gson instance for JSON serialization and deserialization
    private val gson = Gson()

    /**
     * Converts a single DbCollection object to its JSON representation.
     *
     * @param dbCollection The DbCollection object to convert.
     * @return A JSON string representing the DbCollection object.
     */
    @TypeConverter
    fun fromDbCollection(dbCollection: DbCollection): String {
        return gson.toJson(dbCollection)
    }

    /**
     * Converts a JSON string to a DbCollection object.
     *
     * @param json The JSON string representing the DbCollection object.
     * @return The deserialized DbCollection object.
     */
    @TypeConverter
    fun toDbCollection(json: String): DbCollection {
        return gson.fromJson(json, DbCollection::class.java)
    }

}
