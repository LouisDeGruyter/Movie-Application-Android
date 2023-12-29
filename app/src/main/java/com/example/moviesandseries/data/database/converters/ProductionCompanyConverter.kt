package com.example.moviesandseries.data.database.converters

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.DbProductionCompany
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCompanyConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDbProductionCompany(dbProductionCompany: DbProductionCompany): String {
        return gson.toJson(dbProductionCompany)
    }

    @TypeConverter
    fun toDbProductionCompany(json: String): DbProductionCompany {
        return gson.fromJson(json, DbProductionCompany::class.java)
    }

    @TypeConverter
    fun fromDbProductionCompanyList(dbProductionCompanyList: List<DbProductionCompany>): String {
        return gson.toJson(dbProductionCompanyList)
    }

    @TypeConverter
    fun toDbProductionCompanyList(json: String): List<DbProductionCompany> {
        val listType = object : TypeToken<List<DbProductionCompany>>() {}.type
        return gson.fromJson(json, listType)
    }
}
