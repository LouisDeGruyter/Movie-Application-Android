package com.example.moviesandseries.paging.movies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.domain.movie.asMediaIndexObject
import com.example.moviesandseries.repository.MovieRepository

/**
 * [PagingSource] implementation for loading paginated movie data.
 *
 * @param movieRepository The repository for fetching movie data.
 */
class MoviePagingSource(private val movieRepository: MovieRepository) : PagingSource<Int, MediaIndex>() {

    /**
     * Load function that is called to load a chunk of data based on the provided [params].
     *
     * @param params Parameters for loading data, including the requested page key.
     * @return [LoadResult<Int, MediaIndex>] containing the loaded data, with optional adjacent keys.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MediaIndex> {
        return try {
            val page = params.key ?: 1
            val response = movieRepository.getMoviesContainer(page)
            val response2 = movieRepository.getMoviesContainer(page + 1)

            LoadResult.Page(
                data = response.results.map { it.asMediaIndexObject() } + response2.results.map { it.asMediaIndexObject() },
                prevKey = if (page == 1 || page == 2) null else page - 2,
                nextKey = if (page >= response.totalPages - 1) null else page + 2,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    /**
     * Returns the key for refreshing the data based on the current [state].
     *
     * @param state The current [PagingState].
     * @return The key used to refresh the data.
     */
    override fun getRefreshKey(state: PagingState<Int, MediaIndex>): Int? {
        // Try to find the page key of the closest page to anchorPosition from
        // either the prevKey or the nextKey; you need to handle nullability
        // here.
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey are null -> anchorPage is the
        //    initial page, so return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
