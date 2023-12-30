package com.example.domain.series

import com.example.moviesandseries.domain.series.Series

data class SeriesContainer(
    var page: Int = 1,
    var results: List<Series> = listOf(),
    var totalPages: Int = 1,
    var totalResults: Int = 0,
)

