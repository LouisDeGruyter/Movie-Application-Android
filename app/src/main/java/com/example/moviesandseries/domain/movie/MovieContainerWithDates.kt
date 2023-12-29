package com.example.moviesandseries.domain.movie

import com.example.moviesandseries.domain.Dates

data class MovieContainerWithDates(
    var dates: Dates,
    var page: Int,
    var results: List<MovieDetail>,
    var totalPages: Int,
    var totalResults: Int
)

