package com.example.moviesandseries.repository

import com.example.moviesandseries.model.movie.MovieContainer
import com.example.moviesandseries.model.movie.MovieIndex
import com.example.moviesandseries.network.MovieApiService

interface MovieRepository {
    suspend fun getMoviesContainer(): MovieContainer
    suspend fun getMovies():List<MovieIndex>
}

class NetworkMovieRepository(private val movieApiService: MovieApiService) : MovieRepository {
    override suspend fun getMoviesContainer(): MovieContainer = movieApiService.getMoviesContainer()
    override suspend fun getMovies(): List<MovieIndex> = movieApiService.getMoviesContainer().results
}