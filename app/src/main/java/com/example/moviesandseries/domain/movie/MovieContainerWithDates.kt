package com.example.moviesandseries.domain.movie

import com.example.moviesandseries.domain.Dates

data class MovieContainerWithDates(
    var dates: Dates = Dates(),
    var page: Int = 1,
    var results: List<Movie> = listOf(),
    var totalPages: Int = 1,
    var totalResults: Int = 0,
)

