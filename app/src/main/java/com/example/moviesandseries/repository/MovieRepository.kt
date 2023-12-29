package com.example.moviesandseries.repository

import com.example.moviesandseries.domain.credits.CreditsContainer
import com.example.moviesandseries.domain.images.ImagesContainer
import com.example.moviesandseries.domain.movie.MovieContainer
import com.example.moviesandseries.domain.movie.MovieContainerWithDates
import com.example.moviesandseries.domain.movie.MovieDetail
import com.example.moviesandseries.domain.movie.MovieIndex
import com.example.moviesandseries.domain.recommendations.RecommendationContainer
import com.example.moviesandseries.domain.reviews.ReviewContainer
import com.example.moviesandseries.model.credits.asDomainObject
import com.example.moviesandseries.model.images.asDomainObject
import com.example.moviesandseries.model.movie.asDomainObject
import com.example.moviesandseries.model.recommendations.asDomainObject
import com.example.moviesandseries.model.reviews.asDomainObject
import com.example.moviesandseries.model.videos.VideoContainer
import com.example.moviesandseries.model.videos.asDomainObject
import com.example.moviesandseries.network.MovieApiService

interface MovieRepository {
    suspend fun getMoviesContainer(page: Int): MovieContainer
    suspend fun getMovies(page: Int): List<MovieIndex>
    suspend fun getMovieDetail(movieId: Int): MovieDetail
    suspend fun getMovieCredits(movieId: Int): CreditsContainer
    suspend fun getMovieImages(movieId: Int): ImagesContainer
    suspend fun getSimilarMovies(movieId: Int, page: Int): MovieContainer
    suspend fun getRecommendedMovies(movieId: Int, page: Int): RecommendationContainer
    suspend fun getMovieReviews(movieId: Int, page: Int): ReviewContainer
    suspend fun getMoviesInTheaters(page: Int): MovieContainerWithDates
    suspend fun getMoviesPopular(page: Int): MovieContainer
    suspend fun getMoviesTopRated(page: Int): MovieContainer
    suspend fun getMoviesUpcoming(page: Int): MovieContainerWithDates
    suspend fun getMovieVideos(movieId: Int): VideoContainer
}

class NetworkMovieRepository(private val movieApiService: MovieApiService) : MovieRepository {
    override suspend fun getMoviesContainer(page: Int): MovieContainer = movieApiService.getMoviesContainer(page).asDomainObject()
    override suspend fun getMovies(page: Int): List<MovieIndex> = movieApiService.getMoviesContainer(page).results.map { it.asDomainObject() }
    override suspend fun getMovieDetail(movieId: Int): MovieDetail = movieApiService.getMovieDetail(movieId).asDomainObject()
    override suspend fun getMovieCredits(movieId: Int) = movieApiService.getMovieCredits(movieId).asDomainObject()
    override suspend fun getMovieImages(movieId: Int) = movieApiService.getMovieImages(movieId).asDomainObject()
    override suspend fun getSimilarMovies(movieId: Int, page: Int) = movieApiService.getSimilarMovies(movieId = movieId, page = page).asDomainObject()
    override suspend fun getRecommendedMovies(movieId: Int, page: Int) = movieApiService.getRecommendedMovies(movieId = movieId, page = page).asDomainObject()
    override suspend fun getMovieReviews(movieId: Int, page: Int) = movieApiService.getMovieReviews(movieId = movieId, page = page).asDomainObject()
    override suspend fun getMoviesInTheaters(page: Int) = movieApiService.getMoviesInTheaters(page).asDomainObject()
    override suspend fun getMoviesPopular(page: Int) = movieApiService.getMoviesPopular(page).asDomainObject()
    override suspend fun getMoviesTopRated(page: Int) = movieApiService.getMoviesTopRated(page).asDomainObject()
    override suspend fun getMoviesUpcoming(page: Int) = movieApiService.getMoviesUpcoming(page).asDomainObject()
    override suspend fun getMovieVideos(movieId: Int): VideoContainer = movieApiService.getMovieVideos(movieId).asDomainObject()
}
