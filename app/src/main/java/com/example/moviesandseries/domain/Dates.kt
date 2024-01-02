package com.example.moviesandseries.domain

/**
 * Data class representing a pair of dates.
 *
 * @property maximum The maximum date in the format "yyyy-MM-dd".
 * @property minimum The minimum date in the format "yyyy-MM-dd".
 */
data class Dates(
    var maximum: String = "",
    var minimum: String = ""
)
