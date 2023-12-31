package com.example.moviesandseries.repository

import android.util.Log
import com.example.moviesandseries.data.database.dao.MovieDao
import com.example.moviesandseries.data.database.db.asDbObject
import com.example.moviesandseries.data.database.db.asDomainObject
import com.example.moviesandseries.domain.credits.CreditsContainer
import com.example.moviesandseries.domain.images.ImagesContainer
import com.example.moviesandseries.domain.movie.Movie
import com.example.moviesandseries.domain.movie.MovieContainer
import com.example.moviesandseries.domain.movie.MovieContainerWithDates
import com.example.moviesandseries.domain.RecommendationContainer
import com.example.moviesandseries.domain.reviews.ReviewContainer
import com.example.moviesandseries.model.credits.asDomainObject
import com.example.moviesandseries.model.images.asDomainObject
import com.example.moviesandseries.model.movie.asDomainObject
import com.example.moviesandseries.model.recommendations.asDomainObject
import com.example.moviesandseries.model.reviews.asDomainObject
import com.example.moviesandseries.model.videos.VideoContainer
import com.example.moviesandseries.model.videos.asDomainObject
import com.example.moviesandseries.network.MovieApiService
import com.example.moviesandseries.network.getMovieDetailAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.net.SocketTimeoutException

interface MovieRepository {
    suspend fun getMoviesContainer(page: Int): MovieContainer
    suspend fun getMovies(page: Int): List<Movie>
    fun getMovieDetail(movieId: Int): Flow<Movie>
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
    suspend fun refreshMovie(movieId: Int)
    suspend fun insertMovie(movie: Movie)
    suspend fun updateFavorite(currentId: Int, b: Boolean)

    fun getFavoriteMovies(): Flow<List<Movie>>
}

class NetworkMovieRepository(private val movieApiService: MovieApiService, private val movieDao: MovieDao) : MovieRepository {
    override suspend fun getMoviesContainer(page: Int): MovieContainer = try {
        movieApiService.getMoviesContainer(page).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        MovieContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        MovieContainer()
    }
    override suspend fun getMovies(page: Int): List<Movie> = try {
        movieApiService.getMoviesContainer(page).results.map { it.asDomainObject() }
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        listOf()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        listOf()
    }
    override fun getMovieDetail(movieId: Int): Flow<Movie> {
        return movieDao.getItem(movieId).map { it?.asDomainObject() ?: Movie() }
    }
    override suspend fun getMovieCredits(movieId: Int) = try {
        movieApiService.getMovieCredits(movieId).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        CreditsContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        CreditsContainer()
    }
    override suspend fun getMovieImages(movieId: Int) = try {
        movieApiService.getMovieImages(movieId).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        ImagesContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        ImagesContainer()
    }
    override suspend fun getSimilarMovies(movieId: Int, page: Int) = try {
        movieApiService.getSimilarMovies(movieId = movieId, page = page).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        MovieContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        MovieContainer()
    }
    override suspend fun getRecommendedMovies(movieId: Int, page: Int) = try {
        movieApiService.getRecommendedMovies(movieId = movieId, page = page).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        RecommendationContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        RecommendationContainer()
    }
    override suspend fun getMovieReviews(movieId: Int, page: Int) = try {
        movieApiService.getMovieReviews(movieId = movieId, page = page).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        ReviewContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        ReviewContainer()
    }
    override suspend fun getMoviesInTheaters(page: Int) = try {
        movieApiService.getMoviesInTheaters(page).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        MovieContainerWithDates()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        MovieContainerWithDates()
    }
    override suspend fun getMoviesPopular(page: Int) = try {
        movieApiService.getMoviesPopular(page).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        MovieContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        MovieContainer()
    }
    override suspend fun getMoviesTopRated(page: Int) = try {
        movieApiService.getMoviesTopRated(page).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        MovieContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        MovieContainer()
    }
    override suspend fun getMoviesUpcoming(page: Int) = try {
        movieApiService.getMoviesUpcoming(page).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        MovieContainerWithDates()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        MovieContainerWithDates()
    }
    override suspend fun getMovieVideos(movieId: Int): VideoContainer = try {
        movieApiService.getMovieVideos(movieId).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        VideoContainer()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        VideoContainer()
    }

    override suspend fun insertMovie(movie: Movie) {
        val cachedMovie = this.getMovieDetail(movie.id).first()
        if (cachedMovie.id != 0 && cachedMovie.title != "") {
            movie.isFavorite = cachedMovie.isFavorite
        }
        movieDao.insert(movie.asDbObject())
    }

    override suspend fun updateFavorite(currentId: Int, b: Boolean) {
        movieDao.updateFavorite(currentId, b)
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return movieDao.getAllFavoriteItems().map { it.asDomainObject() }
    }

    override suspend fun refreshMovie(movieId: Int) {
        try {
            movieApiService.getMovieDetailAsFlow(movieId).collect {
                insertMovie(it.asDomainObject())
            }
        } catch (e: SocketTimeoutException) {
          Log.e("SocketTimeoutException", "SocketTimeoutException")
        } catch (e: Exception) {
          //throw e
        }
    }
}
