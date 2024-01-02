package com.example.moviesandseries.data.database.converters

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.DbSpokenLanguage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for converting DbSpokenLanguage objects to and from JSON strings.
 */
class SpokenLanguageConverter {
    // Create a Gson instance for JSON serialization and deserialization
    private val gson = Gson()

    /**
     * Converts a list of DbSpokenLanguage objects to its JSON representation.
     *
     * @param dbSpokenLanguageList The list of DbSpokenLanguage objects to convert.
     * @return A JSON string representing the list of DbSpokenLanguage objects.
     */
    @TypeConverter
    fun fromDbSpokenLanguageList(dbSpokenLanguageList: List<DbSpokenLanguage>): String {
        return gson.toJson(dbSpokenLanguageList)
    }

    /**
     * Converts a JSON string to a list of DbSpokenLanguage objects.
     *
     * @param json The JSON string representing the list of DbSpokenLanguage objects.
     * @return The deserialized list of DbSpokenLanguage objects.
     */
    @TypeConverter
    fun toDbSpokenLanguageList(json: String): List<DbSpokenLanguage> {
        // Define the type of the list using TypeToken
        val listType = object : TypeToken<List<DbSpokenLanguage>>() {}.type
        // Deserialize the JSON string to a list of DbSpokenLanguage objects
        return gson.fromJson(json, listType)
    }
}
