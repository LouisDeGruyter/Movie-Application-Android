package com.example.moviesandseries.domain.images

/**
 * Data class representing a container for various images associated with a movie or series.
 *
 * @property backdrops List of backdrop images.
 * @property id The unique identifier for the movie or series.
 * @property logos List of logo images.
 * @property posters List of poster images.
 */
data class ImagesContainer(
    var backdrops: List<Image> = listOf(),
    var id: Int = 0,
    var logos: List<Image> = listOf(),
    var posters: List<Image> = listOf(),
)
