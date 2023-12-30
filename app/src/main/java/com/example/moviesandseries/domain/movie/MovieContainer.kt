package com.example.moviesandseries.domain.movie

data class MovieContainer(
    var page: Int = 1,
    var results: List<MovieDetail> = listOf(),
    var totalPages: Int = 1,
    var totalResults: Int = 0,
)
