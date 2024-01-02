package com.example.moviesandseries.domain.credits

/**
 * Data class representing information about a credit associated with a movie or series.
 *
 * @property adult Indicates if the credit is associated with an adult content (default is false).
 * @property castId Unique identifier for the cast member associated with the credit.
 * @property character The character name associated with the credit.
 * @property creditId Unique identifier for the credit.
 * @property gender Gender of the person associated with the credit (0 for unspecified, 1 for female, 2 for male).
 * @property id Unique identifier for the person associated with the credit.
 * @property knownForDepartment Department in the film industry for which the person is known.
 * @property name Name of the person associated with the credit.
 * @property order Order in which the person appears in the cast or crew list.
 * @property originalName The original name of the person associated with the credit.
 * @property popularity Popularity score associated with the person.
 * @property profilePath Path to the profile image of the person.
 */
data class Credit(
    var adult: Boolean = false,
    var castId: Int = 0,
    var character: String = "",
    var creditId: String = "",
    var gender: Int = 0,
    var id: Int = 0,
    var knownForDepartment: String = "",
    var name: String = "",
    var order: Int = 0,
    var originalName: String = "",
    var popularity: Double = 0.0,
    var profilePath: String = ""
)
