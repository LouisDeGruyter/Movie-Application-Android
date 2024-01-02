package com.example.moviesandseries.data.database.converters.series

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.series.DbCredit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for converting DbCredit objects to and from JSON strings.
 */
class CreditConverter {
    // Create a Gson instance for JSON serialization and deserialization
    private val gson = Gson()

}
