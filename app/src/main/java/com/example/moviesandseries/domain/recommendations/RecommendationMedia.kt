package com.example.moviesandseries.domain.recommendations

data class RecommendationMedia(
    var adult: Boolean,
    var backdropPath: String,
    var genreIds: List<Int>,
    var id: Int,
    var mediaType: String,
    var originalLanguage: String,
    var originalTitle: String,
    var overview: String,
    var popularity: Double,
    var posterPath: String,
    var releaseDate: String,
    var title: String,
    var video: Boolean,
    var voteAverage: Double,
    var voteCount: Int
)

