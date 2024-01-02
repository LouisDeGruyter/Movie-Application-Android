package com.example.moviesandseries.repository

import android.util.Log
import com.example.moviesandseries.domain.series.Season
import com.example.moviesandseries.model.series.season.asDomainObject
import com.example.moviesandseries.network.SeasonApiService
import java.net.SocketTimeoutException

/**
 * Repository interface for managing TV series season data.
 */
interface SeasonRepository {
    /**
     * Retrieves details of a TV series season.
     *
     * @param seriesId The ID of the TV series.
     * @param seasonId The ID of the season.
     * @return A [Season] object containing season details.
     */
    suspend fun getSeasonDetail(seriesId: Int, seasonId: Int): Season
}

/**
 * Implementation of [SeasonRepository] for fetching TV series season data from the network.
 *
 * @param seasonApiService The API service for retrieving TV series season data.
 */
class NetworkSeasonRepository(private val seasonApiService: SeasonApiService) : SeasonRepository {
    /**
     * Retrieves details of a TV series season from the network.
     *
     * @param seriesId The ID of the TV series.
     * @param seasonId The ID of the season.
     * @return A [Season] object containing season details.
     */
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
