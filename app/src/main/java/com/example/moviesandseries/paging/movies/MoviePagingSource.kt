package com.example.moviesandseries.paging.movies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesandseries.repository.MovieRepository

class MoviePagingSource(private val movieRepository: MovieRepository) : PagingSource<Int, MovieIndex>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieIndex> {
        return try {
            val page = params.key ?: 1
            val response = movieRepository.getMoviesContainer(page)
            val response2 = movieRepository.getMoviesContainer(page + 1)

            LoadResult.Page(
                data = response.results + response2.results,
                prevKey = if (page == 1||page ==2) null else page - 2,
                nextKey = if (page >= response.totalPages-1) null else page + 2
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