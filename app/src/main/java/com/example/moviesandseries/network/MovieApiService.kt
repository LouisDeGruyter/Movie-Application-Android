package com.example.moviesandseries.network

import com.example.moviesandseries.model.movie.MovieContainer
import com.example.moviesandseries.model.movie.MovieDetail
import com.example.moviesandseries.model.movie.MovieIndex
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
}