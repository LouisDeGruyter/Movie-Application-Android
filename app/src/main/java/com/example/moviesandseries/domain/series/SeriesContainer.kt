package com.example.moviesandseries.domain.series

data class SeriesContainer(
    var page: Int,
    var results: List<SeriesIndex>,
    var totalPages: Int,
    var totalResults: Int
)

