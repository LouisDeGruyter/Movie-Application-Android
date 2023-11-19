package com.example.moviesandseries.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesandseries.model.movie.MovieIndex
import com.example.moviesandseries.repository.MovieRepository

class MoviePagingSource(private val movieRepository: MovieRepository) : PagingSource<Int, MovieIndex>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieIndex> {
        return try {
            val page = params.key ?: 1
            val response = movieRepository.getMoviesContainer(page)
            Log.d("MoviePagingSource", "load: ${response.results}")
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page >= response.totalPages) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieIndex>): Int? {
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