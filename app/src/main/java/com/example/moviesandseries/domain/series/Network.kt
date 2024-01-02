package com.example.moviesandseries.domain.series

/**
 * Data class representing a network associated with a TV series.
 *
 * @property id The unique identifier for the network.
 * @property logoPath The file path to the network's logo.
 * @property name The name of the network.
 * @property originCountry The country of origin for the network.
 */
data class Network(
    var id: Int = 0,
    var logoPath: String = "",
    var name: String = "",
    var originCountry: String = "",
)
