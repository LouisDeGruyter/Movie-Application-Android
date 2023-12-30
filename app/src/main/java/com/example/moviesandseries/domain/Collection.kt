package com.example.moviesandseries.domain

import com.example.moviesandseries.domain.movie.Movie

data class Collection(
    var backdropPath: String = "",
    var id: Int = 0,
    var name: String = "",
    var overview: String = "",
    var parts: List<Movie> = listOf(),
    var posterPath: String = "",
)