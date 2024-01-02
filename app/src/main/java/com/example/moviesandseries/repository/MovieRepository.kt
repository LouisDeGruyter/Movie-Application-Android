package com.example.moviesandseries.repository

import android.util.Log
import com.example.moviesandseries.data.database.dao.MovieDao
import com.example.moviesandseries.data.database.db.movies.asDbObject
import com.example.moviesandseries.data.database.db.movies.asDomainObject
import com.example.moviesandseries.domain.RecommendationContainer
import com.example.moviesandseries.domain.credits.CreditsContainer
import com.example.moviesandseries.domain.images.ImagesContainer
import com.example.moviesandseries.domain.movie.Movie
import com.example.moviesandseries.domain.movie.MovieContainer
import com.example.moviesandseries.domain.movie.MovieContainerWithDates
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

/**
 * Repository interface for managing movie data.
 */
interface MovieRepository {
    /**
     * Fetches a container of movies based on the specified page.
     *
     * @param page The page number to fetch.
     * @return A [MovieContainer] object containing movie data.
     */
    suspend fun getMoviesContainer(page: Int): MovieContainer

    /**
     * Fetches a list of movies based on the specified page.
     *
     * @param page The page number to fetch.
     * @return A list of [Movie] objects.
     */
    suspend fun getMovies(page: Int): List<Movie>

    /**
     * Retrieves details of a movie as a [Flow] object, allowing for observation of changes.
     *
     * @param movieId The ID of the movie to fetch details for.
     * @return A [Flow] emitting the [Movie] object with the specified ID.
     */
    fun getMovieDetail(movieId: Int): Flow<Movie>

    /**
     * Fetches credits information for a movie.
     *
     * @param movieId The ID of the movie to fetch credits for.
     * @return A [CreditsContainer] object containing credits data.
     */
    suspend fun getMovieCredits(movieId: Int): CreditsContainer

    /**
     * Fetches images associated with a movie.
     *
     * @param movieId The ID of the movie to fetch images for.
     * @return An [ImagesContainer] object containing images data.
     */
    suspend fun getMovieImages(movieId: Int): ImagesContainer

    /**
     * Fetches a container of movies similar to the specified movie.
     *
     * @param movieId The ID of the reference movie.
     * @param page The page number to fetch.
     * @return A [MovieContainer] object containing similar movies data.
     */
    suspend fun getSimilarMovies(movieId: Int, page: Int): MovieContainer

    /**
     * Fetches a container of recommended movies for the specified movie.
     *
     * @param movieId The ID of the reference movie.
     * @param page The page number to fetch.
     * @return A [RecommendationContainer] object containing recommended movies data.
     */
    suspend fun getRecommendedMovies(movieId: Int, page: Int): RecommendationContainer

    /**
     * Fetches reviews for a movie.
     *
     * @param movieId The ID of the movie to fetch reviews for.
     * @param page The page number to fetch.
     * @return A [ReviewContainer] object containing reviews data.
     */
    suspend fun getMovieReviews(movieId: Int, page: Int): ReviewContainer

    /**
     * Fetches movies currently in theaters.
     *
     * @param page The page number to fetch.
     * @return A [MovieContainerWithDates] object containing in-theater movies data.
     */
    suspend fun getMoviesInTheaters(page: Int): MovieContainerWithDates

    /**
     * Fetches popular movies.
     *
     * @param page The page number to fetch.
     * @return A [MovieContainer] object containing popular movies data.
     */
    suspend fun getMoviesPopular(page: Int): MovieContainer

    /**
     * Fetches top-rated movies.
     *
     * @param page The page number to fetch.
     * @return A [MovieContainer] object containing top-rated movies data.
     */
    suspend fun getMoviesTopRated(page: Int): MovieContainer

    /**
     * Fetches upcoming movies.
     *
     * @param page The page number to fetch.
     * @return A [MovieContainerWithDates] object containing upcoming movies data.
     */
    suspend fun getMoviesUpcoming(page: Int): MovieContainerWithDates

    /**
     * Fetches videos associated with a movie.
     *
     * @param movieId The ID of the movie to fetch videos for.
     * @return A [VideoContainer] object containing videos data.
     */
    suspend fun getMovieVideos(movieId: Int): VideoContainer

    /**
     * Refreshes the details of a movie.
     *
     * @param movieId The ID of the movie to refresh.
     */
    suspend fun refreshMovie(movieId: Int)

    /**
     * Inserts a movie into the repository.
     *
     * @param movie The [Movie] object to be inserted.
     */
    suspend fun insertMovie(movie: Movie)

    /**
     * Updates the favorite status of a movie.
     *
     * @param currentId The ID of the movie to update.
     * @param b The new favorite status.
     */
    suspend fun updateFavorite(currentId: Int, b: Boolean)

    /**
     * Retrieves a [Flow] of all favorite movies.
     *
     * @return A [Flow] emitting a list of [Movie] objects.
     */
    fun getFavoriteMovies(): Flow<List<Movie>>
}

/**
* Implementation of [MovieRepository] for fetching movie data from the network and caching in the local database.
*
* @param movieApiService The API service for retrieving movie data.
* @param movieDao The DAO for accessing movie data in the local database.
*/
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
            Log.e("Exception", e.message.toString())
        }
    }
}
