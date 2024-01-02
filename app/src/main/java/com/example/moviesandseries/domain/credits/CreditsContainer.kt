package com.example.moviesandseries.domain.credits

/**
 * Data class representing a container for credits associated with a movie or series.
 *
 * @property cast List of credits for the cast members.
 * @property crew List of credits for the crew members.
 * @property id Unique identifier associated with the movie or series.
 */
data class CreditsContainer(
    var cast: List<Credit> = listOf(),
    var crew: List<Credit> = listOf(),
    var id: Int = 0,
)
