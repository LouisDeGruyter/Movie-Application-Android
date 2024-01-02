package com.example.moviesandseries.domain.series

/**
 * Data class representing a creator or author of a TV series.
 *
 * @property creditId The unique identifier for the credit associated with the creator.
 * @property gender The gender of the creator (0 for undefined, 1 for female, 2 for male).
 * @property id The unique identifier for the creator.
 * @property name The name of the creator.
 * @property profilePath The file path to the profile image of the creator.
 */
data class CreatedBy(
    var creditId: String = "",
    var gender: Int = 0,
    var id: Int = 0,
    var name: String = "",
    var profilePath: String = "",
)
