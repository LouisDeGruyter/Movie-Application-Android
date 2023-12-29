package com.example.moviesandseries.domain.Collection

import com.example.moviesandseries.domain.movie.MovieIndex

data class CollectionDetail(
    var backdropPath: String = "",
    var id: Int = 0,
    var name: String = "",
    var overview: String = "",
    var parts: List<MovieIndex> = listOf(),
    var posterPath: String = "",
)