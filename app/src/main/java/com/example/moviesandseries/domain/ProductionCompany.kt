package com.example.moviesandseries.domain

/**
 * Data class representing a production company associated with a movie or series.
 *
 * @property id The unique identifier for the production company.
 * @property logoPath The path to the logo image for the production company.
 * @property name The name of the production company.
 * @property originCountry The origin country of the production company.
 */
data class ProductionCompany(
    var id: Int = 0,
    var logoPath: String = "",
    var name: String = "",
    var originCountry: String = ""
)
