package com.example.moviesandseries.domain.series

/**
 * Data class representing a season of a TV series.
 *
 * @property airDate The date when the season first aired.
 * @property episodeCount The total number of episodes in the season.
 * @property id The unique identifier for the season.
 * @property name The name of the season.
 * @property overview A brief overview or summary of the season.
 * @property posterPath The file path to the poster of the season.
 * @property seasonNumber The season number.
 * @property voteAverage The average vote rating for the season.
 * @property episodes The list of episodes in the season.
 */
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
