package com.example.moviesandseries.data

import com.example.moviesandseries.repository.CollectionRepository
import com.example.moviesandseries.repository.MovieRepository
import com.example.moviesandseries.repository.SeasonRepository
import com.example.moviesandseries.repository.SeriesRepository

/**
 * A dependency container for providing repositories related to movies, series, collections, and seasons.
 */
interface AppContainer {
    /**
     * Provides access to the repository for movie-related operations.
     */
    val movieRepository: MovieRepository

    /**
     * Provides access to the repository for series-related operations.
     */
    val seriesRepository: SeriesRepository

    /**
     * Provides access to the repository for collection-related operations.
     */
    val collectionRepository: CollectionRepository

    /**
     * Provides access to the repository for season-related operations.
     */
    val seasonRepository: SeasonRepository
}
