package com.example.moviesandseries.domain.series

import com.example.moviesandseries.domain.credits.Credit

/**
 * Data class representing an episode of a TV series.
 *
 * @property airDate The air date of the episode.
 * @property episodeNumber The episode number within its season.
 * @property id The unique identifier for the episode.
 * @property name The name of the episode.
 * @property overview A brief overview or summary of the episode.
 * @property productionCode The production code associated with the episode.
 * @property runtime The duration of the episode in minutes.
 * @property seasonNumber The season number to which the episode belongs.
 * @property showId The unique identifier for the TV series to which the episode belongs.
 * @property stillPath The file path to the still image associated with the episode.
 * @property voteAverage The average user rating for the episode.
 * @property voteCount The total number of user votes for the episode.
 * @property crew The list of credits for the crew involved in the episode.
 * @property episodeType The type of the episode (e.g., "standard", "double", "triple").
 * @property guestStars The list of credits for the guest stars in the episode.
 */
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
