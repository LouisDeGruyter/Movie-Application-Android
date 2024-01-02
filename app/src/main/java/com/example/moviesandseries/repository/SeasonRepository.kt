package com.example.moviesandseries.repository

import android.util.Log
import com.example.moviesandseries.domain.series.Season
import com.example.moviesandseries.model.series.season.asDomainObject
import com.example.moviesandseries.network.SeasonApiService
import java.net.SocketTimeoutException

interface SeasonRepository {
    suspend fun getSeasonDetail(seriesId: Int, seasonId: Int): Season
}

class NetworkSeasonRepository(private val seasonApiService: SeasonApiService) : SeasonRepository {
    override suspend fun getSeasonDetail(seriesId: Int, seasonId: Int): Season = try {
        seasonApiService.getSeasonDetail(seriesId, seasonId).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        Season()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        Season()
    }
}
