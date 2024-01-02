package com.example.moviesandseries.data.database.converters.series

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.series.DbCredit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CreditConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDbCreditList(dbCreditList: List<DbCredit>): String {
        return gson.toJson(dbCreditList)
    }

    @TypeConverter
    fun toDbCreditList(json: String): List<DbCredit> {
        val listType = object : TypeToken<List<DbCredit>>() {}.type
        return gson.fromJson(json, listType)
    }
}
