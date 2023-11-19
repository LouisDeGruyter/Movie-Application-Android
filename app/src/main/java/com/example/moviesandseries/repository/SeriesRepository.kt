package com.example.moviesandseries.repository

import com.example.moviesandseries.model.series.SeriesContainer
import com.example.moviesandseries.model.series.SeriesDetail
import com.example.moviesandseries.model.series.SeriesIndex
import com.example.moviesandseries.network.SeriesApiService

interface SeriesRepository {
    suspend fun getSeriesContainer(): SeriesContainer
    suspend fun getSeries(): List<SeriesIndex>
    suspend fun getSeriesDetail(seriesId: Int): SeriesDetail

}
class NetworkSeriesRepository(private val seriesApiService: SeriesApiService) : SeriesRepository {
    override suspend fun getSeriesContainer(): SeriesContainer = seriesApiService.getSeriesContainer()
    override suspend fun getSeries(): List<SeriesIndex> = seriesApiService.getSeriesContainer().results
    override suspend fun getSeriesDetail(seriesId: Int): SeriesDetail = seriesApiService.getSeriesDetail(seriesId)
}