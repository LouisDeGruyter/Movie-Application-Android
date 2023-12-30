package com.example.moviesandseries.domain.images

data class Image(
    var aspectRatio: Double = 0.0,
    var filePath: String = "",
    var height: Int = 0,
    var iso6391: String = "",
    var voteAverage: Double = 0.0,
    var voteCount: Int = 0,
    var width: Int = 0,
)
