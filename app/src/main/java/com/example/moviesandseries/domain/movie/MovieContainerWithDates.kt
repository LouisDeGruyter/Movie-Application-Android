package com.example.moviesandseries.domain.movie

import com.example.moviesandseries.domain.Dates

/**
 * Data class representing a container for a list of movies with date information.
 *
 * @property dates Date information for the movie list.
 * @property page The current page of the movie list.
 * @property results List of [Movie] objects representing the movies on the current page.
 * @property totalPages The total number of pages available.
 * @property totalResults The total number of movies available across all pages.
 */
data class MovieContainerWithDates(
    var dates: Dates = Dates(),
    var page: Int = 1,
    var results: List<Movie> = listOf(),
    var totalPages: Int = 1,
    var totalResults: Int = 0,
)
