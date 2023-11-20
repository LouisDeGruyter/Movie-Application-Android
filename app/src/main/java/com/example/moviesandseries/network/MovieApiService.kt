package com.example.moviesandseries.network

import com.example.moviesandseries.model.credits.CreditsContainer
import com.example.moviesandseries.model.images.ImagesContainer
import com.example.moviesandseries.model.movie.MovieContainer
import com.example.moviesandseries.model.movie.MovieDetail
import com.example.moviesandseries.model.movie.MovieContainerWithDates
import com.example.moviesandseries.model.recommendations.RecommendationContainer
import com.example.moviesandseries.model.reviews.ReviewContainer
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET(ApiEndpoints.Movies)
    suspend fun getMoviesContainer(@Query("page") page: Int): MovieContainer
    @GET(ApiEndpoints.MovieDetail)
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int
    ): MovieDetail
    @GET(ApiEndpoints.MovieDetail+"/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
    ): CreditsContainer
    @GET(ApiEndpoints.MovieDetail+"/images")
    suspend fun getMovieImages(
        @Path("movie_id") movieId: Int,
    ): ImagesContainer
    @GET(ApiEndpoints.MovieDetail+"/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int, @Query("page") page: Int
    ): MovieContainer
    @GET(ApiEndpoints.MovieDetail+"/recommendations")
    suspend fun getRecommendedMovies(
        @Path("movie_id") movieId: Int, @Query("page") page: Int
    ): RecommendationContainer
    @GET(ApiEndpoints.MovieDetail+"/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int, @Query("page") page: Int
    ): ReviewContainer

    @GET(ApiEndpoints.MoviesInTheaters)
    suspend fun getMoviesInTheaters(
        @Query("page") page: Int
    ): MovieContainerWithDates
    @GET(ApiEndpoints.MoviesPopular)
    suspend fun getMoviesPopular(
        @Query("page") page: Int
    ): MovieContainer
    @GET(ApiEndpoints.MoviesTopRated)
    suspend fun getMoviesTopRated(
        @Query("page") page: Int
    ): MovieContainer
    @GET(ApiEndpoints.MoviesUpcoming)
    suspend fun getMoviesUpcoming(
        @Query("page") page: Int
    ): MovieContainerWithDates

}