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

interface SeriesRepository {
    suspend fun getSeriesContainer(page: Int): SeriesContainer
    suspend fun getSeries(page: Int): List<Series>
    fun getSeriesDetail(seriesId: Int): Flow<Series>
    suspend fun getSeriesCredits(seriesId: Int): CreditsContainer

    suspend fun getSeriesImages(seriesId: Int): ImagesContainer
    suspend fun getSimilarSeries(seriesId: Int, page: Int): SeriesContainer
    suspend fun getRecommendedSeries(seriesId: Int, page: Int): RecommendationContainer
    suspend fun getSeriesReviews(seriesId: Int, page: Int): ReviewContainer
    suspend fun getSeriesPopular(page: Int): SeriesContainer
    suspend fun getSeriesTopRated(page: Int): SeriesContainer
    suspend fun getSeriesOnTheAir(page: Int): SeriesContainer
    suspend fun getSeriesAiringToday(page: Int): SeriesContainer
    suspend fun getSeriesVideos(seriesId: Int): VideoContainer
    suspend fun refreshSeries(seriesId: Int)
    suspend fun updateFavorite(currentId: Int, b: Boolean)
    suspend fun insertSeries(series: Series)
    fun getFavoriteSeries(): Flow<List<Series>>
}
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
