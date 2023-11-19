package com.example.moviesandseries.network

import com.example.moviesandseries.model.movie.MovieContainer
import com.example.moviesandseries.model.movie.MovieDetail
import com.example.moviesandseries.model.movie.MovieIndex
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    @GET(ApiEndpoints.Movies)
    suspend fun getMoviesContainer(): MovieContainer
    @GET(ApiEndpoints.MovieDetail)
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int
    ): MovieDetail
}