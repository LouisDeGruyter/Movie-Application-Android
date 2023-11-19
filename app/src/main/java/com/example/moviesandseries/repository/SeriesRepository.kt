package com.example.moviesandseries.repository

import com.example.moviesandseries.model.series.SeriesContainer
import com.example.moviesandseries.model.series.SeriesDetail
import com.example.moviesandseries.model.series.SeriesIndex
import com.example.moviesandseries.network.SeriesApiService

interface SeriesRepository {
    suspend fun getSeriesContainer(page:Int): SeriesContainer
    suspend fun getSeries(page:Int): List<SeriesIndex>
    suspend fun getSeriesDetail(seriesId: Int): SeriesDetail

}
class NetworkSeriesRepository(private val seriesApiService: SeriesApiService) : SeriesRepository {
    override suspend fun getSeriesContainer(page:Int): SeriesContainer = seriesApiService.getSeriesContainer(page)
    override suspend fun getSeries(page:Int): List<SeriesIndex> = seriesApiService.getSeriesContainer(page).results
    override suspend fun getSeriesDetail(seriesId: Int): SeriesDetail = seriesApiService.getSeriesDetail(seriesId)
}