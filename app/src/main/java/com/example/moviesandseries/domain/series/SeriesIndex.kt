package com.example.moviesandseries.domain.series

data class SeriesIndex(
    var backdropPath: String?,
    var firstAirDate: String?,
    var genreIds: List<Int>,
    var id: Int,
    var name: String,
    var originCountry: List<String>,
    var originalLanguage: String,
    var originalName: String?,
    var overview: String,
    var popularity: Double,
    var posterPath: String?,
    var voteAverage: Double,
    var voteCount: Int
)

