package com.example.moviesandseries.paging.series

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.domain.series.asMediaIndexObject
import com.example.moviesandseries.repository.SeriesRepository

/**
 * [PagingSource] implementation for loading paginated series data.
 *
 * @param seriesRepository The repository for fetching series data.
 */
class SeriesPagingSource(
    private val seriesRepository: SeriesRepository
) : PagingSource<Int, MediaIndex>() {

    /**
     * Load function that is called to load a chunk of series data based on the provided [params].
     *
     * @param params Parameters for loading data, including the requested page key.
     * @return [LoadResult<Int, MediaIndex>] containing the loaded series data, with optional adjacent keys.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MediaIndex> {
        return try {
            val page = params.key ?: 1
            val response = seriesRepository.getSeriesContainer(page = page)
            LoadResult.Page(
                data = response.results.map { it.asMediaIndexObject() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page >= response.totalPages) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    /**
     * Returns the key for refreshing the series data based on the current [state].
     *
     * @param state The current [PagingState].
     * @return The key used to refresh the series data.
     */
    override fun getRefreshKey(state: PagingState<Int, MediaIndex>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
