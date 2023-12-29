package com.example.moviesandseries.network

import com.example.moviesandseries.model.credits.CreditsContainerApi
import com.example.moviesandseries.model.images.ImagesContainerApi
import com.example.moviesandseries.model.recommendations.RecommendationContainerApi
import com.example.moviesandseries.model.reviews.ReviewContainerApi
import com.example.moviesandseries.model.series.SeriesContainerApi
import com.example.moviesandseries.model.series.SeriesDetailApi
import com.example.moviesandseries.model.videos.VideoContainerApi
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesApiService {
    @GET(ApiEndpoints.Series)
    suspend fun getSeriesContainer(@Query("page") page: Int): SeriesContainerApi

    @GET(ApiEndpoints.SeriesDetail)
    suspend fun getSeriesDetail(@Path("series_id") seriesId: Int): SeriesDetailApi

    @GET(ApiEndpoints.SeriesDetail + "/credits")
    suspend fun getSeriesCredits(
        @Path("series_id") seriesId: Int,
    ): CreditsContainerApi

    @GET(ApiEndpoints.SeriesDetail + "/images")
    suspend fun getSeriesImages(
        @Path("series_id") seriesId: Int,
    ): ImagesContainerApi

    @GET(ApiEndpoints.SeriesDetail + "/similar")
    suspend fun getSimilarSeries(
        @Path("series_id") seriesId: Int,
        @Query("page") page: Int,
    ): SeriesContainerApi

    @GET(ApiEndpoints.SeriesDetail + "/recommendations")
    suspend fun getRecommendedSeries(
        @Path("series_id") seriesId: Int,
        @Query("page") page: Int,
    ): RecommendationContainerApi

    @GET(ApiEndpoints.SeriesDetail + "/reviews")
    suspend fun getSeriesReviews(
        @Path("series_id") seriesId: Int,
        @Query("page") page: Int,
    ): ReviewContainerApi

    @GET(ApiEndpoints.SeriesPopular)
    suspend fun getSeriesPopular(
        @Query("page") page: Int,
    ): SeriesContainerApi

    @GET(ApiEndpoints.SeriesTopRated)
    suspend fun getSeriesTopRated(
        @Query("page") page: Int,
    ): SeriesContainerApi

    @GET(ApiEndpoints.SeriesOnTheAir)
    suspend fun getSeriesOnTheAir(
        @Query("page") page: Int,
    ): SeriesContainerApi

    @GET(ApiEndpoints.SeriesAiringToday)
    suspend fun getSeriesAiringToday(
        @Query("page") page: Int,
    ): SeriesContainerApi

   @GET(ApiEndpoints.SeriesDetail + "/videos")
    suspend fun getSeriesVideos(
        @Path("series_id") seriesId: Int,
    ): VideoContainerApi
}
