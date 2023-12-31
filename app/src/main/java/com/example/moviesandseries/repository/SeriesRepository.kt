package com.example.moviesandseries.repository

import com.example.moviesandseries.domain.credits.CreditsContainer
import com.example.moviesandseries.domain.images.ImagesContainer
import com.example.moviesandseries.domain.RecommendationContainer
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

interface SeriesRepository {
    suspend fun getSeriesContainer(page: Int): SeriesContainer
    suspend fun getSeries(page: Int): List<Series>
    suspend fun getSeriesDetail(seriesId: Int): Series
    suspend fun getSeriesCredits(seriesId: Int): CreditsContainer

    suspend fun getSeriesImages(seriesId: Int): ImagesContainer
    suspend fun getSimilarSeries(seriesId: Int, page: Int): SeriesContainer
    suspend fun getRecommendedSeries(seriesId: Int, page: Int): RecommendationContainer
    suspend fun getSeriesReviews(seriesId: Int, page: Int): ReviewContainer
    suspend fun getSeriesPopular(page: Int): SeriesContainer
    suspend fun getSeriesTopRated(page: Int): SeriesContainer
    suspend fun getSeriesOnTheAir(page: Int): SeriesContainer
    suspend fun getSeriesAiringToday(page: Int): SeriesContainer
    suspend fun getSeriesVideos(movieId: Int): VideoContainer
}
class NetworkSeriesRepository(private val seriesApiService: SeriesApiService) : SeriesRepository {
    override suspend fun getSeriesContainer(page: Int): SeriesContainer = seriesApiService.getSeriesContainer(page).asDomainObject()
    override suspend fun getSeries(page: Int): List<Series> = seriesApiService.getSeriesContainer(page).results.map { it.asDomainObject() }
    override suspend fun getSeriesDetail(seriesId: Int): Series = seriesApiService.getSeriesDetail(seriesId).asDomainObject()
    override suspend fun getSeriesCredits(seriesId: Int) = seriesApiService.getSeriesCredits(seriesId).asDomainObject()
    override suspend fun getSeriesImages(seriesId: Int) = seriesApiService.getSeriesImages(seriesId).asDomainObject()
    override suspend fun getSimilarSeries(seriesId: Int, page: Int) = seriesApiService.getSimilarSeries(seriesId = seriesId, page = page).asDomainObject()
    override suspend fun getRecommendedSeries(seriesId: Int, page: Int) = seriesApiService.getRecommendedSeries(seriesId = seriesId, page = page).asDomainObject()
    override suspend fun getSeriesReviews(seriesId: Int, page: Int) = seriesApiService.getSeriesReviews(seriesId = seriesId, page = page).asDomainObject()
    override suspend fun getSeriesPopular(page: Int) = seriesApiService.getSeriesPopular(page).asDomainObject()
    override suspend fun getSeriesTopRated(page: Int) = seriesApiService.getSeriesTopRated(page).asDomainObject()
    override suspend fun getSeriesOnTheAir(page: Int) = seriesApiService.getSeriesOnTheAir(page).asDomainObject()
    override suspend fun getSeriesAiringToday(page: Int) = seriesApiService.getSeriesAiringToday(page).asDomainObject()
    override suspend fun getSeriesVideos(movieId: Int): VideoContainer = seriesApiService.getSeriesVideos(movieId).asDomainObject()
}
