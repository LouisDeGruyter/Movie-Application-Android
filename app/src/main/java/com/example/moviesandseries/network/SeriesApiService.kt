package com.example.moviesandseries.network

import com.example.moviesandseries.model.credits.CreditsContainerApi
import com.example.moviesandseries.model.images.ImagesContainerApi
import com.example.moviesandseries.model.recommendations.RecommendationContainerApi
import com.example.moviesandseries.model.reviews.ReviewContainerApi
import com.example.moviesandseries.model.series.SeriesContainerApi
import com.example.moviesandseries.model.series.SeriesDetailApi
import com.example.moviesandseries.model.videos.VideoContainerApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit service interface for fetching details related to TV series.
 */
interface SeriesApiService {

    /**
     * Fetches a container of TV series.
     *
     * @param page The page number for paginated results.
     * @return A container of TV series.
     */
    @GET(ApiEndpoints.Series)
    suspend fun getSeriesContainer(@Query("page") page: Int): SeriesContainerApi

    /**
     * Fetches details of a specific TV series.
     *
     * @param seriesId The ID of the TV series.
     * @return Details of the specified TV series.
     */
    @GET("tv/{series_id}")
    suspend fun getSeriesDetail(@Path("series_id") seriesId: Int): SeriesDetailApi

    /**
     * Fetches credits for a specific TV series.
     *
     * @param seriesId The ID of the TV series.
     * @return Credits for the specified TV series.
     */
    @GET(ApiEndpoints.SeriesDetail + "/credits")
    suspend fun getSeriesCredits(@Path("series_id") seriesId: Int): CreditsContainerApi

    /**
     * Fetches images related to a specific TV series.
     *
     * @param seriesId The ID of the TV series.
     * @return Images related to the specified TV series.
     */
    @GET(ApiEndpoints.SeriesDetail + "/images")
    suspend fun getSeriesImages(@Path("series_id") seriesId: Int): ImagesContainerApi

    /**
     * Fetches TV series similar to a specific TV series.
     *
     * @param seriesId The ID of the TV series.
     * @param page The page number for paginated results.
     * @return TV series similar to the specified TV series.
     */
    @GET(ApiEndpoints.SeriesDetail + "/similar")
    suspend fun getSimilarSeries(@Path("series_id") seriesId: Int, @Query("page") page: Int): SeriesContainerApi

    /**
     * Fetches recommended TV series for a specific TV series.
     *
     * @param seriesId The ID of the TV series.
     * @param page The page number for paginated results.
     * @return Recommended TV series for the specified TV series.
     */
    @GET(ApiEndpoints.SeriesDetail + "/recommendations")
    suspend fun getRecommendedSeries(@Path("series_id") seriesId: Int, @Query("page") page: Int): RecommendationContainerApi

    /**
     * Fetches reviews for a specific TV series.
     *
     * @param seriesId The ID of the TV series.
     * @param page The page number for paginated results.
     * @return Reviews for the specified TV series.
     */
    @GET(ApiEndpoints.SeriesDetail + "/reviews")
    suspend fun getSeriesReviews(@Path("series_id") seriesId: Int, @Query("page") page: Int): ReviewContainerApi

    /**
     * Fetches popular TV series.
     *
     * @param page The page number for paginated results.
     * @return Popular TV series.
     */
    @GET(ApiEndpoints.SeriesPopular)
    suspend fun getSeriesPopular(@Query("page") page: Int): SeriesContainerApi

    /**
     * Fetches top-rated TV series.
     *
     * @param page The page number for paginated results.
     * @return Top-rated TV series.
     */
    @GET(ApiEndpoints.SeriesTopRated)
    suspend fun getSeriesTopRated(@Query("page") page: Int): SeriesContainerApi

    /**
     * Fetches currently airing TV series.
     *
     * @param page The page number for paginated results.
     * @return Currently airing TV series.
     */
    @GET(ApiEndpoints.SeriesOnTheAir)
    suspend fun getSeriesOnTheAir(@Query("page") page: Int): SeriesContainerApi

    /**
     * Fetches TV series airing today.
     *
     * @param page The page number for paginated results.
     * @return TV series airing today.
     */
    @GET(ApiEndpoints.SeriesAiringToday)
    suspend fun getSeriesAiringToday(@Query("page") page: Int): SeriesContainerApi

    /**
     * Fetches videos related to a specific TV series.
     *
     * @param seriesId The ID of the TV series.
     * @return Videos related to the specified TV series.
     */
    @GET(ApiEndpoints.SeriesDetail + "/videos")
    suspend fun getSeriesVideos(@Path("series_id") seriesId: Int): VideoContainerApi
}

/**
 * Extension function to convert the result of getSeriesDetail into a [Flow].
 *
 * @param seriesId The ID of the TV series.
 * @return A [Flow] emitting the details of the specified TV series.
 */
fun SeriesApiService.getSeriesDetailAsFlow(seriesId: Int): Flow<SeriesDetailApi> = flow {
    emit(getSeriesDetail(seriesId))
}
