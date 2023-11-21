package com.example.moviesandseries.network

import com.example.moviesandseries.model.credits.CreditsContainer
import com.example.moviesandseries.model.images.ImagesContainer
import com.example.moviesandseries.model.recommendations.RecommendationContainer
import com.example.moviesandseries.model.reviews.ReviewContainer
import com.example.moviesandseries.model.series.SeriesContainer
import com.example.moviesandseries.model.series.SeriesDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesApiService {
    @GET(ApiEndpoints.Series)
    suspend fun getSeriesContainer(@Query("page") page: Int): SeriesContainer

    @GET(ApiEndpoints.SeriesDetail)
    suspend fun getSeriesDetail(@Path("series_id") seriesId: Int): SeriesDetail

    @GET(ApiEndpoints.SeriesDetail + "/credits")
    suspend fun getSeriesCredits(
        @Path("series_id") seriesId: Int,
    ): CreditsContainer

    @GET(ApiEndpoints.SeriesDetail + "/images")
    suspend fun getSeriesImages(
        @Path("series_id") seriesId: Int,
    ): ImagesContainer

    @GET(ApiEndpoints.SeriesDetail + "/similar")
    suspend fun getSimilarSeries(
        @Path("series_id") seriesId: Int,
        @Query("page") page: Int,
    ): SeriesContainer

    @GET(ApiEndpoints.SeriesDetail + "/recommendations")
    suspend fun getRecommendedSeries(
        @Path("series_id") seriesId: Int,
        @Query("page") page: Int,
    ): RecommendationContainer

    @GET(ApiEndpoints.SeriesDetail + "/reviews")
    suspend fun getSeriesReviews(
        @Path("series_id") seriesId: Int,
        @Query("page") page: Int,
    ): ReviewContainer

    @GET(ApiEndpoints.SeriesPopular)
    suspend fun getSeriesPopular(
        @Query("page") page: Int,
    ): SeriesContainer

    @GET(ApiEndpoints.SeriesTopRated)
    suspend fun getSeriesTopRated(
        @Query("page") page: Int,
    ): SeriesContainer

    @GET(ApiEndpoints.SeriesOnTheAir)
    suspend fun getSeriesOnTheAir(
        @Query("page") page: Int,
    ): SeriesContainer

    @GET(ApiEndpoints.SeriesAiringToday)
    suspend fun getSeriesAiringToday(
        @Query("page") page: Int,
    ): SeriesContainer
}
