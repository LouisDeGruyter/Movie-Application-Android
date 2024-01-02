package com.example.moviesandseries.network

import com.example.moviesandseries.model.credits.CreditsContainerApi
import com.example.moviesandseries.model.images.ImagesContainerApi
import com.example.moviesandseries.model.movie.MovieContainerApi
import com.example.moviesandseries.model.movie.MovieContainerWithDatesApi
import com.example.moviesandseries.model.movie.MovieDetailApi
import com.example.moviesandseries.model.recommendations.RecommendationContainerApi
import com.example.moviesandseries.model.reviews.ReviewContainerApi
import com.example.moviesandseries.model.videos.VideoContainerApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit service interface for making API requests related to movies.
 */
interface MovieApiService {

    /**
     * Retrieves a container of movies based on the provided page.
     *
     * @param page The page number of the movies to retrieve.
     * @return A suspend function returning the [MovieContainerApi] containing a list of movies.
     */
    @GET(ApiEndpoints.Movies)
    suspend fun getMoviesContainer(@Query("page") page: Int): MovieContainerApi

    /**
     * Retrieves details for a specific movie based on the provided movie ID.
     *
     * @param movieId The unique identifier of the movie.
     * @return A suspend function returning the [MovieDetailApi] containing details of the movie.
     */
    @GET(ApiEndpoints.MovieDetail)
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): MovieDetailApi

    /**
     * Retrieves credits for a specific movie based on the provided movie ID.
     *
     * @param movieId The unique identifier of the movie.
     * @return A suspend function returning the [CreditsContainerApi] containing credits for the movie.
     */
    @GET(ApiEndpoints.MovieDetail + "/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: Int): CreditsContainerApi

    /**
     * Retrieves images for a specific movie based on the provided movie ID.
     *
     * @param movieId The unique identifier of the movie.
     * @return A suspend function returning the [ImagesContainerApi] containing images for the movie.
     */
    @GET(ApiEndpoints.MovieDetail + "/images")
    suspend fun getMovieImages(@Path("movie_id") movieId: Int): ImagesContainerApi

    /**
     * Retrieves a list of movies similar to a specific movie based on the provided movie ID and page.
     *
     * @param movieId The unique identifier of the movie.
     * @param page The page number of the similar movies to retrieve.
     * @return A suspend function returning the [MovieContainerApi] containing a list of similar movies.
     */
    @GET(ApiEndpoints.MovieDetail + "/similar")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int, @Query("page") page: Int): MovieContainerApi

    /**
     * Retrieves a list of recommended movies for a specific movie based on the provided movie ID and page.
     *
     * @param movieId The unique identifier of the movie.
     * @param page The page number of the recommended movies to retrieve.
     * @return A suspend function returning the [RecommendationContainerApi] containing a list of recommended movies.
     */
    @GET(ApiEndpoints.MovieDetail + "/recommendations")
    suspend fun getRecommendedMovies(@Path("movie_id") movieId: Int, @Query("page") page: Int): RecommendationContainerApi

    /**
     * Retrieves reviews for a specific movie based on the provided movie ID and page.
     *
     * @param movieId The unique identifier of the movie.
     * @param page The page number of the reviews to retrieve.
     * @return A suspend function returning the [ReviewContainerApi] containing reviews for the movie.
     */
    @GET(ApiEndpoints.MovieDetail + "/reviews")
    suspend fun getMovieReviews(@Path("movie_id") movieId: Int, @Query("page") page: Int): ReviewContainerApi

    /**
     * Retrieves a container of movies currently in theaters based on the provided page.
     *
     * @param page The page number of the movies in theaters to retrieve.
     * @return A suspend function returning the [MovieContainerWithDatesApi] containing a list of movies in theaters.
     */
    @GET(ApiEndpoints.MoviesInTheaters)
    suspend fun getMoviesInTheaters(@Query("page") page: Int): MovieContainerWithDatesApi

    /**
     * Retrieves a container of popular movies based on the provided page.
     *
     * @param page The page number of the popular movies to retrieve.
     * @return A suspend function returning the [MovieContainerApi] containing a list of popular movies.
     */
    @GET(ApiEndpoints.MoviesPopular)
    suspend fun getMoviesPopular(@Query("page") page: Int): MovieContainerApi

    /**
     * Retrieves a container of top-rated movies based on the provided page.
     *
     * @param page The page number of the top-rated movies to retrieve.
     * @return A suspend function returning the [MovieContainerApi] containing a list of top-rated movies.
     */
    @GET(ApiEndpoints.MoviesTopRated)
    suspend fun getMoviesTopRated(@Query("page") page: Int): MovieContainerApi

    /**
     * Retrieves a container of upcoming movies based on the provided page.
     *
     * @param page The page number of the upcoming movies to retrieve.
     * @return A suspend function returning the [MovieContainerWithDatesApi] containing a list of upcoming movies.
     */
    @GET(ApiEndpoints.MoviesUpcoming)
    suspend fun getMoviesUpcoming(@Query("page") page: Int): MovieContainerWithDatesApi

    /**
     * Retrieves videos (trailers, teasers, etc.) for a specific movie based on the provided movie ID.
     *
     * @param movieId The unique identifier of the movie.
     * @return A suspend function returning the [VideoContainerApi] containing videos for the movie.
     */
    @GET(ApiEndpoints.MovieDetail + "/videos")
    suspend fun getMovieVideos(@Path("movie_id") movieId: Int): VideoContainerApi
}

/**
 * Extension function to convert the getMovieDetail function to a Flow.
 *
 * @param movieId The unique identifier of the movie.
 * @return A Flow emitting the [MovieDetailApi] containing details of the movie.
 */
fun MovieApiService.getMovieDetailAsFlow(movieId: Int): Flow<MovieDetailApi> = flow {
    emit(getMovieDetail(movieId))
}
