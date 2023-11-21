package com.example.moviesandseries.repository

import com.example.moviesandseries.model.recommendations.RecommendationContainerApi
import com.example.moviesandseries.network.MovieApiService

interface MovieRepository {
    suspend fun getMoviesContainer(page: Int): MovieContainer
    suspend fun getMovies(page: Int): List<MovieIndex>
    suspend fun getMovieDetail(movieId: Int): MovieDetail
    suspend fun getMovieCredits(movieId: Int): CreditsContainer
    suspend fun getMovieImages(movieId: Int): ImagesContainer
    suspend fun getSimilarMovies(movieId: Int, page: Int): MovieContainer
    suspend fun getRecommendedMovies(movieId: Int, page: Int): RecommendationContainerApi
    suspend fun getMovieReviews(movieId: Int, page: Int): ReviewContainer
    suspend fun getMoviesInTheaters(page: Int): MovieContainerWithDates
    suspend fun getMoviesPopular(page: Int): MovieContainer
    suspend fun getMoviesTopRated(page: Int): MovieContainer
    suspend fun getMoviesUpcoming(page: Int): MovieContainerWithDates
}

class NetworkMovieRepository(private val movieApiService: MovieApiService) : MovieRepository {
    override suspend fun getMoviesContainer(page: Int): MovieContainer = movieApiService.getMoviesContainer(page)
    override suspend fun getMovies(page: Int): List<MovieIndex> = movieApiService.getMoviesContainer(page).results
    override suspend fun getMovieDetail(movieId: Int): MovieDetail = movieApiService.getMovieDetail(movieId)
    override suspend fun getMovieCredits(movieId: Int) = movieApiService.getMovieCredits(movieId)
    override suspend fun getMovieImages(movieId: Int) = movieApiService.getMovieImages(movieId)
    override suspend fun getSimilarMovies(movieId: Int, page: Int) = movieApiService.getSimilarMovies(movieId = movieId, page = page)
    override suspend fun getRecommendedMovies(movieId: Int, page: Int) = movieApiService.getRecommendedMovies(movieId = movieId, page = page)
    override suspend fun getMovieReviews(movieId: Int, page: Int) = movieApiService.getMovieReviews(movieId = movieId, page = page)
    override suspend fun getMoviesInTheaters(page: Int) = movieApiService.getMoviesInTheaters(page)
    override suspend fun getMoviesPopular(page: Int) = movieApiService.getMoviesPopular(page)
    override suspend fun getMoviesTopRated(page: Int) = movieApiService.getMoviesTopRated(page)
    override suspend fun getMoviesUpcoming(page: Int) = movieApiService.getMoviesUpcoming(page)
}
