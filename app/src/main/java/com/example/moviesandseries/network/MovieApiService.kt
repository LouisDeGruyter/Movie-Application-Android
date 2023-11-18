package com.example.moviesandseries.network

import com.example.moviesandseries.model.movie.MovieContainer
import retrofit2.http.GET

interface MovieApiService {
    @GET(ApiEndpoints.Movies)
    suspend fun getMoviesContainer(): MovieContainer
}