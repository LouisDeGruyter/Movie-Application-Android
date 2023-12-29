package com.example.moviesandseries.data.database.converters

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.DbProductionCountry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCountryConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDbProductionCountry(dbProductionCountry: DbProductionCountry): String {
        return gson.toJson(dbProductionCountry)
    }

    @TypeConverter
    fun toDbProductionCountry(json: String): DbProductionCountry {
        return gson.fromJson(json, DbProductionCountry::class.java)
    }

    @TypeConverter
    fun fromDbProductionCountryList(dbProductionCountryList: List<DbProductionCountry>): String {
        return gson.toJson(dbProductionCountryList)
    }

    @TypeConverter
    fun toDbProductionCountryList(json: String): List<DbProductionCountry> {
        val listType = object : TypeToken<List<DbProductionCountry>>() {}.type
        return gson.fromJson(json, listType)
    }
}
