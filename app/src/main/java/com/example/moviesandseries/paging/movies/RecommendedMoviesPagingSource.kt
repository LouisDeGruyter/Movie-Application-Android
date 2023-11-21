package com.example.moviesandseries.paging.movies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesandseries.domain.recommendations.RecommendationMedia
import com.example.moviesandseries.repository.MovieRepository

class RecommendedMoviesPagingSource(private val movieRepository: MovieRepository, private val movieId: Int) : PagingSource<Int, RecommendationMedia>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecommendationMedia> {
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

    override fun getRefreshKey(state: PagingState<Int, RecommendationMedia>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
