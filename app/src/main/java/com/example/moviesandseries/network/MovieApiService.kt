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

interface MovieApiService {
    @GET(ApiEndpoints.Movies)
    suspend fun getMoviesContainer(@Query("page") page: Int): MovieContainerApi

    @GET(ApiEndpoints.MovieDetail)
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
    ): MovieDetailApi

    @GET(ApiEndpoints.MovieDetail + "/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
    ): CreditsContainerApi

    @GET(ApiEndpoints.MovieDetail + "/images")
    suspend fun getMovieImages(
        @Path("movie_id") movieId: Int,
    ): ImagesContainerApi

    @GET(ApiEndpoints.MovieDetail + "/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int,
    ): MovieContainerApi

    @GET(ApiEndpoints.MovieDetail + "/recommendations")
    suspend fun getRecommendedMovies(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int,
    ): RecommendationContainerApi

    @GET(ApiEndpoints.MovieDetail + "/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int,
    ): ReviewContainerApi

    @GET(ApiEndpoints.MoviesInTheaters)
    suspend fun getMoviesInTheaters(
        @Query("page") page: Int,
    ): MovieContainerWithDatesApi

    @GET(ApiEndpoints.MoviesPopular)
    suspend fun getMoviesPopular(
        @Query("page") page: Int,
    ): MovieContainerApi

    @GET(ApiEndpoints.MoviesTopRated)
    suspend fun getMoviesTopRated(
        @Query("page") page: Int,
    ): MovieContainerApi

    @GET(ApiEndpoints.MoviesUpcoming)
    suspend fun getMoviesUpcoming(
        @Query("page") page: Int,
    ): MovieContainerWithDatesApi

    @GET(ApiEndpoints.MovieDetail + "/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
    ): VideoContainerApi
}

fun MovieApiService.getMovieDetailAsFlow(movieId: Int): Flow<MovieDetailApi> = flow {
    emit(getMovieDetail(movieId))
}
