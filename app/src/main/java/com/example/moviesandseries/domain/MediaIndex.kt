package com.example.moviesandseries.domain

/**
 * Data class representing an index for media items (movies or series).
 *
 * @property adult Indicates if the media item is intended for adult audiences.
 * @property backdropPath The path to the backdrop image for the media item.
 * @property genreIds The list of genre IDs associated with the media item.
 * @property id The unique identifier for the media item.
 * @property mediaType The type of media item, either "movie" or "tv" for series.
 * @property originalLanguage The original language of the media item.
 * @property originalTitle The original title of the media item.
 * @property overview A brief overview or description of the media item.
 * @property popularity The popularity score of the media item.
 * @property posterPath The path to the poster image for the media item.
 * @property releaseDate The release date of the media item.
 * @property title The title of the media item.
 * @property video Indicates if the media item has video content.
 * @property voteAverage The average vote score for the media item.
 * @property voteCount The total number of votes received by the media item.
 * @property originCountry The list of origin countries for the media item (for series).
 * @property isFavorite Indicates if the media item is marked as a favorite.
 */
data class MediaIndex(
    var adult: Boolean = false,
    var backdropPath: String = "",
    var genreIds: List<Int> = listOf(),
    var id: Int = 0,
    var mediaType: String = "",
    var originalLanguage: String = "",
    var originalTitle: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var posterPath: String = "",
    var releaseDate: String = "",
    var title: String = "",
    var video: Boolean = false,
    var voteAverage: Double = 0.0,
    var voteCount: Int = 0,
    var originCountry: List<String>? = listOf(),
    val isFavorite: Boolean = false
)
