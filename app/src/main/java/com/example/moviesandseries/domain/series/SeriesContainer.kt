package com.example.moviesandseries.domain.series

/**
 * Data class representing a container of TV series.
 *
 * @property page The current page number of the series container.
 * @property results The list of [Series] objects in the container.
 * @property totalPages The total number of pages available.
 * @property totalResults The total number of series available.
 */
data class SeriesContainer(
    var page: Int = 1,
    var results: List<Series> = listOf(),
    var totalPages: Int = 1,
    var totalResults: Int = 0,
)
