package com.example.moviesandseries.domain.movie

data class MovieContainer(
    var page: Int,
    var results: List<MovieDetail>,
    var totalPages: Int,
    var totalResults: Int
)
