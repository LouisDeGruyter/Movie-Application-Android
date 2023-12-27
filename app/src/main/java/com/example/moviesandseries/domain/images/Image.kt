package com.example.moviesandseries.domain.images

data class Image(
    var aspectRatio: Double,
    var filePath: String,
    var height: Int,
    var iso6391: String?,
    var voteAverage: Double,
    var voteCount: Int,
    var width: Int
)
