package com.example.moviesandseries.repository

import com.example.moviesandseries.model.movie.MovieContainer
import com.example.moviesandseries.model.movie.MovieDetail
import com.example.moviesandseries.model.movie.MovieIndex
import com.example.moviesandseries.network.MovieApiService

interface MovieRepository {
    suspend fun getMoviesContainer(page:Int): MovieContainer
    suspend fun getMovies(page:Int):List<MovieIndex>
    suspend fun getMovieDetail(movieId: Int): MovieDetail
}

class NetworkMovieRepository(private val movieApiService: MovieApiService) : MovieRepository {
    override suspend fun getMoviesContainer(page:Int): MovieContainer = movieApiService.getMoviesContainer(page)
    override suspend fun getMovies(page:Int): List<MovieIndex> = movieApiService.getMoviesContainer(page).results
    override suspend fun getMovieDetail(movieId: Int): MovieDetail = movieApiService.getMovieDetail(movieId)
}