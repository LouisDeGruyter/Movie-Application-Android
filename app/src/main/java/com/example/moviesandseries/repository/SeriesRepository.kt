package com.example.moviesandseries.repository

import android.util.Log
import com.example.moviesandseries.data.database.dao.SeriesDao
import com.example.moviesandseries.data.database.db.series.asDbObject
import com.example.moviesandseries.data.database.db.series.asDomainObject
import com.example.moviesandseries.domain.RecommendationContainer
import com.example.moviesandseries.domain.credits.CreditsContainer
import com.example.moviesandseries.domain.images.ImagesContainer
import com.example.moviesandseries.domain.reviews.ReviewContainer
import com.example.moviesandseries.domain.series.Series
import com.example.moviesandseries.domain.series.SeriesContainer
import com.example.moviesandseries.model.credits.asDomainObject
import com.example.moviesandseries.model.images.asDomainObject
import com.example.moviesandseries.model.recommendations.asDomainObject
import com.example.moviesandseries.model.reviews.asDomainObject
import com.example.moviesandseries.model.series.asDomainObject
import com.example.moviesandseries.model.videos.VideoContainer
import com.example.moviesandseries.model.videos.asDomainObject
import com.example.moviesandseries.network.SeriesApiService
import com.example.moviesandseries.network.getSeriesDetailAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.net.SocketTimeoutException

/**
 * Repository interface for managing TV series data.
 */
interface SeriesRepository {
    /**
     * Retrieves a container of TV series data.
     *
     * @param page The page number to retrieve.
     * @return A [SeriesContainer] object containing TV series data.
     */
    suspend fun getSeriesContainer(page: Int): SeriesContainer

    /**
     * Retrieves a list of TV series.
     *
     * @param page The page number to retrieve.
     * @return A list of [Series] objects.
     */
    suspend fun getSeries(page: Int): List<Series>

    /**
     * Retrieves details of a TV series.
     *
     * @param seriesId The ID of the TV series.
     * @return A [Flow] emitting the [Series] object with the specified ID, or null if not found.
     */
    fun getSeriesDetail(seriesId: Int): Flow<Series>

    /**
     * Retrieves credits for a TV series.
     *
     * @param seriesId The ID of the TV series.
     * @return A [CreditsContainer] object containing credits data.
     */
    suspend fun getSeriesCredits(seriesId: Int): CreditsContainer

    /**
     * Retrieves images for a TV series.
     *
     * @param seriesId The ID of the TV series.
     * @return An [ImagesContainer] object containing images data.
     */
    suspend fun getSeriesImages(seriesId: Int): ImagesContainer

    /**
     * Retrieves similar TV series.
     *
     * @param seriesId The ID of the TV series.
     * @param page The page number to retrieve.
     * @return A [SeriesContainer] object containing similar TV series data.
     */
    suspend fun getSimilarSeries(seriesId: Int, page: Int): SeriesContainer

    /**
     * Retrieves recommended TV series.
     *
     * @param seriesId The ID of the TV series.
     * @param page The page number to retrieve.
     * @return A [RecommendationContainer] object containing recommended TV series data.
     */
    suspend fun getRecommendedSeries(seriesId: Int, page: Int): RecommendationContainer

    /**
     * Retrieves reviews for a TV series.
     *
     * @param seriesId The ID of the TV series.
     * @param page The page number to retrieve.
     * @return A [ReviewContainer] object containing reviews data.
     */
    suspend fun getSeriesReviews(seriesId: Int, page: Int): ReviewContainer

    /**
     * Retrieves popular TV series.
     *
     * @param page The page number to retrieve.
     * @return A [SeriesContainer] object containing popular TV series data.
     */
    suspend fun getSeriesPopular(page: Int): SeriesContainer

    /**
     * Retrieves top-rated TV series.
     *
     * @param page The page number to retrieve.
     * @return A [SeriesContainer] object containing top-rated TV series data.
     */
    suspend fun getSeriesTopRated(page: Int): SeriesContainer

    /**
     * Retrieves currently airing TV series.
     *
     * @param page The page number to retrieve.
     * @return A [SeriesContainer] object containing currently airing TV series data.
     */
    suspend fun getSeriesOnTheAir(page: Int): SeriesContainer

    /**
     * Retrieves TV series airing today.
     *
     * @param page The page number to retrieve.
     * @return A [SeriesContainer] object containing TV series airing today data.
     */
    suspend fun getSeriesAiringToday(page: Int): SeriesContainer

    /**
     * Retrieves videos for a TV series.
     *
     * @param seriesId The ID of the TV series.
     * @return A [VideoContainer] object containing videos data.
     */
    suspend fun getSeriesVideos(seriesId: Int): VideoContainer

    /**
     * Refreshes the TV series data in the repository.
     *
     * @param seriesId The ID of the TV series to refresh.
     */
    suspend fun refreshSeries(seriesId: Int)

    /**
     * Inserts a TV series into the repository.
     *
     * @param series The [Series] object to be inserted.
     */
    suspend fun insertSeries(series: Series)

    /**
     * Updates the favorite status of a TV series in the repository.
     *
     * @param currentId The ID of the TV series.
     * @param b The new favorite status.
     */
    suspend fun updateFavorite(currentId: Int, b: Boolean)

    /**
     * Retrieves a [Flow] of all favorite TV series in the repository.
     *
     * @return A [Flow] emitting a list of [Series] objects.
     */
    fun getFavoriteSeries(): Flow<List<Series>>
}

/**
 * Implementation of [SeriesRepository] for caching TV series data.
 *
 * @param seriesApiService The API service for retrieving TV series data.
 * @param seriesDao The DAO for accessing TV series data in the local database.
 */
class NetworkSeriesRepository(private val seriesApiService: SeriesApiService, private val seriesDao: SeriesDao) : SeriesRepository {
    override suspend fun getSeriesContainer(page: Int): SeriesContainer = try {
        seriesApiService.getSeriesContainer(page).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        SeriesContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        SeriesContainer()
    }
    override suspend fun getSeries(page: Int): List<Series> = try {
        seriesApiService.getSeriesContainer(page).results.map { it.asDomainObject() }
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        listOf()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        listOf()
    }
    override fun getSeriesDetail(seriesId: Int): Flow<Series> {
        return seriesDao.getItem(seriesId).map { it?.asDomainObject() ?: Series() }
    }
    override suspend fun getSeriesCredits(seriesId: Int) = try {
        seriesApiService.getSeriesCredits(seriesId).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        CreditsContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        CreditsContainer()
    }
    override suspend fun getSeriesImages(seriesId: Int) = try {
        seriesApiService.getSeriesImages(seriesId).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        ImagesContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        ImagesContainer()
    }
    override suspend fun getSimilarSeries(seriesId: Int, page: Int) = try {
        seriesApiService.getSimilarSeries(seriesId = seriesId, page = page).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        SeriesContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        SeriesContainer()
    }
    override suspend fun getRecommendedSeries(seriesId: Int, page: Int) = try {
        seriesApiService.getRecommendedSeries(seriesId = seriesId, page = page).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        RecommendationContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        RecommendationContainer()
    }
    override suspend fun getSeriesReviews(seriesId: Int, page: Int) = try {
        seriesApiService.getSeriesReviews(seriesId = seriesId, page = page).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        ReviewContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        ReviewContainer()
    }
    override suspend fun getSeriesPopular(page: Int) = try {
        seriesApiService.getSeriesPopular(page).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        SeriesContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        SeriesContainer()
    }
    override suspend fun getSeriesTopRated(page: Int) = try {
        seriesApiService.getSeriesTopRated(page).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        SeriesContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        SeriesContainer()
    }
    override suspend fun getSeriesOnTheAir(page: Int) = try {
        seriesApiService.getSeriesOnTheAir(page).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        SeriesContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        SeriesContainer()
    }
    override suspend fun getSeriesAiringToday(page: Int) = try {
        seriesApiService.getSeriesAiringToday(page).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        SeriesContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        SeriesContainer()
    }
    override suspend fun getSeriesVideos(seriesId: Int): VideoContainer = try {
        val videos = seriesApiService.getSeriesVideos(seriesId).asDomainObject()
        videos
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        VideoContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        VideoContainer()
    }
    override suspend fun refreshSeries(seriesId: Int) {
        try {
            seriesApiService.getSeriesDetailAsFlow(seriesId).collect {
                insertSeries(it.asDomainObject())
            }
        } catch (e: SocketTimeoutException) {
            Log.e("SocketTimeoutException", "SocketTimeoutException")
        } catch (e: Exception) {
            Log.e("Exception refreshseries", e.message.toString())
        }
    }
    override suspend fun insertSeries(series: Series) {
        val cachedSeries = this.getSeriesDetail(series.id).first()
        if (cachedSeries.id != 0 && cachedSeries.name != "") {
            series.isFavorite = cachedSeries.isFavorite
        }
        seriesDao.insert(series.asDbObject())
    }

    override suspend fun updateFavorite(currentId: Int, b: Boolean) {
        seriesDao.updateFavorite(currentId, b)
    }
    override fun getFavoriteSeries(): Flow<List<Series>> {
        return seriesDao.getAllFavoriteItems().map { it.asDomainObject() }
    }
}
