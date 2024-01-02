package com.example.moviesandseries.domain.images

/**
 * Data class representing an image associated with a movie or series.
 *
 * @property aspectRatio The aspect ratio of the image.
 * @property filePath The file path to the image.
 * @property height The height of the image in pixels.
 * @property iso6391 The ISO 639-1 language code for the image.
 * @property voteAverage The average vote for the image.
 * @property voteCount The number of votes for the image.
 * @property width The width of the image in pixels.
 */
data class Image(
    var aspectRatio: Double = 0.0,
    var filePath: String = "",
    var height: Int = 0,
    var iso6391: String = "",
    var voteAverage: Double = 0.0,
    var voteCount: Int = 0,
    var width: Int = 0,
)
