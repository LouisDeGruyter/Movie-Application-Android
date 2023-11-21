package com.example.moviesandseries.domain.series.episode

data class Episode(
    var airDate: String?,
    var episodeNumber: Int,
    var id: Int,
    var name: String,
    var overview: String,
    var productionCode: String?,
    var runtime: Int?,
    var seasonNumber: Int,
    var showId: Int,
    var stillPath: String?,
    var voteAverage: Double,
    var voteCount: Int
)

