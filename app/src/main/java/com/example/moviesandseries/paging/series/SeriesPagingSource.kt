package com.example.moviesandseries.paging.series

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesandseries.repository.SeriesRepository

class SeriesPagingSource (private val seriesRepository: SeriesRepository): PagingSource<Int, SeriesIndex>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SeriesIndex> {
        return try {
            val page = params.key ?: 1
            val response = seriesRepository.getSeriesContainer(page=page)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page >= response.totalPages) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SeriesIndex>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}