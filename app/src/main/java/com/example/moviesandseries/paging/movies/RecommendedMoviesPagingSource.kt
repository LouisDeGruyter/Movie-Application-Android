package com.example.moviesandseries.paging.movies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.repository.MovieRepository

/**
 * [PagingSource] implementation for loading paginated recommended movies data.
 *
 * @param movieRepository The repository for fetching recommended movies data.
 * @param movieId The ID of the movie for which recommendations are requested.
 */
class RecommendedMoviesPagingSource(
    private val movieRepository: MovieRepository,
    private val movieId: Int
) : PagingSource<Int, MediaIndex>() {

    /**
     * Load function that is called to load a chunk of recommended movies data based on the provided [params].
     *
     * @param params Parameters for loading data, including the requested page key.
     * @return [LoadResult<Int, MediaIndex>] containing the loaded recommended movies data, with optional adjacent keys.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MediaIndex> {
        return try {
            val page = params.key ?: 1
            val response = movieRepository.getRecommendedMovies(movieId = movieId, page = page)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page >= response.totalPages) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    /**
     * Returns the key for refreshing the recommended movies data based on the current [state].
     *
     * @param state The current [PagingState].
     * @return The key used to refresh the recommended movies data.
     */
    override fun getRefreshKey(state: PagingState<Int, MediaIndex>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
