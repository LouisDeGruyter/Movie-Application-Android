package com.example.moviesandseries.domain

/**
 * Data class representing a production country associated with a movie or series.
 *
 * @property iso3166_1 The ISO 3166-1 alpha-2 code for the production country.
 * @property name The name of the production country.
 */
data class ProductionCountry(
    var iso3166_1: String = "",
    var name: String = ""
)
