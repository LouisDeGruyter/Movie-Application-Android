package com.example.moviesandseries.data.database.converters

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.DbSpokenLanguage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SpokenLanguageConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDbSpokenLanguage(dbSpokenLanguage: DbSpokenLanguage): String {
        return gson.toJson(dbSpokenLanguage)
    }

    @TypeConverter
    fun toDbSpokenLanguage(json: String): DbSpokenLanguage {
        return gson.fromJson(json, DbSpokenLanguage::class.java)
    }

    @TypeConverter
    fun fromDbSpokenLanguageList(dbSpokenLanguageList: List<DbSpokenLanguage>): String {
        return gson.toJson(dbSpokenLanguageList)
    }

    @TypeConverter
    fun toDbSpokenLanguageList(json: String): List<DbSpokenLanguage> {
        val listType = object : TypeToken<List<DbSpokenLanguage>>() {}.type
        return gson.fromJson(json, listType)
    }
}
