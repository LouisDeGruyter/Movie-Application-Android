package com.example.moviesandseries.network

import com.example.moviesandseries.model.series.season.SeasonDetailApi
import retrofit2.http.GET
import retrofit2.http.Path

interface SeasonApiService {
    @GET(ApiEndpoints.SeasonDetail)
    suspend fun getSeasonDetail(@Path("tv_id") seriesId: Int, @Path("season_number") seasonId: Int): SeasonDetailApi
}