package com.example.moviesandseries.domain.movie

data class MovieContainer(
    var page: Int,
    var results: List<MovieIndex>,
    var totalPages: Int,
    var totalResults: Int
)
