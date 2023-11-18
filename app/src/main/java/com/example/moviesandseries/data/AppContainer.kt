package com.example.moviesandseries.data

import com.example.moviesandseries.repository.MovieRepository

interface AppContainer {
    val movieRepository: MovieRepository
}