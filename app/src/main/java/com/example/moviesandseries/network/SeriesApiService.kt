package com.example.moviesandseries.network

import com.example.moviesandseries.model.series.SeriesContainer
import com.example.moviesandseries.model.series.SeriesDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesApiService {
    @GET(ApiEndpoints.Series)
    suspend fun getSeriesContainer(): SeriesContainer
    @GET(ApiEndpoints.SeriesDetail)

    suspend fun getSeriesDetail(@Path("series_id") seriesId: Int): SeriesDetail
}