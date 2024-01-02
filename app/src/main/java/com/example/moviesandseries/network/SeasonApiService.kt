package com.example.moviesandseries.network

import com.example.moviesandseries.model.series.season.SeasonDetailApi
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit service interface for fetching details of a TV series season.
 */
interface SeasonApiService {

    /**
     * Fetches details of a specific season of a TV series.
     *
     * @param seriesId The ID of the TV series.
     * @param seasonId The season number.
     * @return Details of the specified season.
     */
    @GET(ApiEndpoints.SeasonDetail)
    suspend fun getSeasonDetail(@Path("tv_id") seriesId: Int, @Path("season_number") seasonId: Int): SeasonDetailApi
}
