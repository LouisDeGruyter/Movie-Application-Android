package com.example.moviesandseries.domain.series

import com.example.moviesandseries.domain.credits.Credit

data class Episode(
    var airDate: String = "",
    var episodeNumber: Int = 0,
    var id: Int = 0,
    var name: String = "",
    var overview: String = "",
    var productionCode: String = "",
    var runtime: Int = 0,
    var seasonNumber: Int = 0,
    var showId: Int = 0,
    var stillPath: String = "",
    var voteAverage: Double = 0.0,
    var voteCount: Int = 0,
    val crew: List<Credit> = listOf(),
    val episodeType: String = "",
    val guestStars: List<Credit> = listOf(),
)
