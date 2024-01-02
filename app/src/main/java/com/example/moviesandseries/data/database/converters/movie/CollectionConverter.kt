package com.example.moviesandseries.data.database.converters.movie

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.movies.DbCollection
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CollectionConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDbCollection(dbCollection: DbCollection): String {
        return gson.toJson(dbCollection)
    }

    @TypeConverter
    fun toDbCollection(json: String): DbCollection {
        return gson.fromJson(json, DbCollection::class.java)
    }

    @TypeConverter
    fun fromDbCollectionList(dbCollectionList: List<DbCollection>): String {
        return gson.toJson(dbCollectionList)
    }

    @TypeConverter
    fun toDbCollectionList(json: String): List<DbCollection> {
        val listType = object : TypeToken<List<DbCollection>>() {}.type
        return gson.fromJson(json, listType)
    }
}