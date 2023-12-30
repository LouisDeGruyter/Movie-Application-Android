package com.example.moviesandseries.domain.series

data class SeriesContainer(
    var page: Int = 1,
    var results: List<Series> = listOf(),
    var totalPages: Int = 1,
    var totalResults: Int = 0,
)

