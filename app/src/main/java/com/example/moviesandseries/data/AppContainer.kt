package com.example.moviesandseries.data

import com.example.moviesandseries.repository.CollectionRepository
import com.example.moviesandseries.repository.MovieRepository
import com.example.moviesandseries.repository.SeriesRepository

interface AppContainer {
    val movieRepository: MovieRepository
    val seriesRepository: SeriesRepository
    val collectionRepository: CollectionRepository
}