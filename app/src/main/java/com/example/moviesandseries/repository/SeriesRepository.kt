package com.example.moviesandseries.repository

import com.example.moviesandseries.model.credits.CreditsContainer
import com.example.moviesandseries.model.images.ImagesContainer
import com.example.moviesandseries.model.recommendations.RecommendationContainer
import com.example.moviesandseries.model.reviews.ReviewContainer
import com.example.moviesandseries.model.series.SeriesContainer
import com.example.moviesandseries.model.series.SeriesDetail
import com.example.moviesandseries.model.series.SeriesIndex
import com.example.moviesandseries.network.SeriesApiService

interface SeriesRepository {
    suspend fun getSeriesContainer(page:Int): SeriesContainer
    suspend fun getSeries(page:Int): List<SeriesIndex>
    suspend fun getSeriesDetail(seriesId: Int): SeriesDetail
    suspend fun getSeriesCredits(seriesId: Int): CreditsContainer

    suspend fun getSeriesImages(seriesId: Int) : ImagesContainer
    suspend fun getSimilarSeries(seriesId: Int, page: Int) : SeriesContainer
    suspend fun getRecommendedSeries(seriesId: Int,page: Int) : RecommendationContainer
    suspend fun getSeriesReviews(seriesId: Int,page: Int): ReviewContainer
    suspend fun getSeriesPopular(page: Int): SeriesContainer
    suspend fun getSeriesTopRated(page: Int) : SeriesContainer
    suspend fun getSeriesOnTheAir(page: Int): SeriesContainer
    suspend fun getSeriesAiringToday(page: Int): SeriesContainer

}
class NetworkSeriesRepository(private val seriesApiService: SeriesApiService) : SeriesRepository {
    override suspend fun getSeriesContainer(page:Int): SeriesContainer = seriesApiService.getSeriesContainer(page)
    override suspend fun getSeries(page:Int): List<SeriesIndex> = seriesApiService.getSeriesContainer(page).results
    override suspend fun getSeriesDetail(seriesId: Int): SeriesDetail = seriesApiService.getSeriesDetail(seriesId)
    override suspend fun getSeriesCredits(seriesId: Int) = seriesApiService.getSeriesCredits(seriesId)
    override suspend fun getSeriesImages(seriesId: Int) = seriesApiService.getSeriesImages(seriesId)
    override suspend fun getSimilarSeries(seriesId: Int,page: Int) = seriesApiService.getSimilarSeries(seriesId=seriesId,page= page)
    override suspend fun getRecommendedSeries(seriesId: Int,page: Int) = seriesApiService.getRecommendedSeries(seriesId=seriesId,page= page)
    override suspend fun getSeriesReviews(seriesId: Int,page: Int) = seriesApiService.getSeriesReviews(seriesId=seriesId,page= page)
    override suspend fun getSeriesPopular(page: Int) = seriesApiService.getSeriesPopular(page)
    override suspend fun getSeriesTopRated(page: Int) = seriesApiService.getSeriesTopRated(page)
    override suspend fun getSeriesOnTheAir(page: Int) = seriesApiService.getSeriesOnTheAir(page)
    override suspend fun getSeriesAiringToday(page: Int) = seriesApiService.getSeriesAiringToday(page)
    
}