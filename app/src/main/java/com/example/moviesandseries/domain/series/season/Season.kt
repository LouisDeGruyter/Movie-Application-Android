package com.example.moviesandseries.domain.series.season

data class Season(
    var airDate: String?,
    var episodeCount: Int?,
    var id: Int,
    var name: String,
    var overview: String,
    var posterPath: String?,
    var seasonNumber: Int,
    var voteAverage: Double
)

