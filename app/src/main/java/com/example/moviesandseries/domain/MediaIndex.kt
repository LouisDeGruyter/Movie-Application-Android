package com.example.moviesandseries.domain

data class MediaIndex(
    var adult: Boolean = false,
    var backdropPath: String = "",
    var genreIds: List<Int> = listOf(),
    var id: Int = 0,
    var mediaType: String = "",
    var originalLanguage: String = "",
    var originalTitle: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var posterPath: String = "",
    var releaseDate: String = "",
    var title: String = "",
    var video: Boolean = false,
    var voteAverage: Double = 0.0,
    var voteCount: Int = 0,
    var originCountry: List<String>? = listOf(),
    val isFavorite: Boolean = false,
)
