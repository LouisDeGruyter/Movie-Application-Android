package com.example.moviesandseries.paging.movies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesandseries.model.movie.MovieIndex
import com.example.moviesandseries.repository.MovieRepository

class PopularMoviesPagingSource(private val movieRepository: MovieRepository) : PagingSource<Int, MovieIndex>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieIndex> {
        return try {
            val page = params.key ?: 1
            val response = movieRepository.getMoviesPopular(page = page)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page >= response.totalPages) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieIndex>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
