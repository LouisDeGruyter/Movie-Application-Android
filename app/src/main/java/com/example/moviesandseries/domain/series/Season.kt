package com.example.moviesandseries.domain.series

data class Season(
    var airDate: String = "",
    var episodeCount: Int = 0,
    var id: Int = 0,
    var name: String = "",
    var overview: String = "",
    var posterPath: String = "",
    var seasonNumber: Int = 0,
    var voteAverage: Double = 0.0,
    val episodes: List<Episode> = listOf(),
)

