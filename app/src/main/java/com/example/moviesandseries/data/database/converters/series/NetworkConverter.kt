package com.example.moviesandseries.data.database.converters.series

import androidx.room.TypeConverter
import com.example.moviesandseries.data.database.db.series.DbNetwork
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NetworkConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDbNetwork(dbNetwork: DbNetwork): String {
        return gson.toJson(dbNetwork)
    }

    @TypeConverter
    fun toDbNetwork(json: String): DbNetwork {
        return gson.fromJson(json, DbNetwork::class.java)
    }

    @TypeConverter
    fun fromDbNetworkList(dbNetworkList: List<DbNetwork>): String {
        return gson.toJson(dbNetworkList)
    }

    @TypeConverter
    fun toDbNetworkList(json: String): List<DbNetwork> {
        val listType = object : TypeToken<List<DbNetwork>>() {}.type
        return gson.fromJson(json, listType)
    }
}
