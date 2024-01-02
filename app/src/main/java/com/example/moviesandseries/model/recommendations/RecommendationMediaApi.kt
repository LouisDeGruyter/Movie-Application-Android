package com.example.moviesandseries.model.recommendations

import com.example.moviesandseries.domain.MediaIndex
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a media item in recommendations.
 *
 * @property adult Indicates if the media item is intended for an adult audience.
 * @property backdropPath The path to the backdrop image of the media item.
 * @property genreIds List of genre IDs associated with the media item.
 * @property id The unique identifier of the media item.
 * @property mediaType The type of media (e.g., movie, TV show).
 * @property originalLanguage The original language of the media item.
 * @property originalTitle The original title of the media item.
 * @property overview A brief overview or synopsis of the media item.
 * @property popularity The popularity score of the media item.
 * @property posterPath The path to the poster image of the media item.
 * @property releaseDate The release date of the media item.
 * @property title The title of the media item.
 * @property video Indicates if the media item has a video.
 * @property voteAverage The average vote score of the media item.
 * @property voteCount The total number of votes received by the media item.
 */
@JsonClass(generateAdapter = true)
data class RecommendationMediaApi(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "media_type")
    val mediaType: String,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String?,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "release_date")
    val releaseDate: String?,
    @Json(name = "title")
    val title: String? = "",
    @Json(name = "video")
    val video: Boolean? = false,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
)

/**
 * Extension function to convert [RecommendationMediaApi] to [MediaIndex] domain object.
 */
fun RecommendationMediaApi.asDomainObject(): MediaIndex {
    return MediaIndex(
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        id = id,
        mediaType = mediaType,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle ?: "",
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate ?: "",
        title = title ?: "",
        video = video ?: false,
        voteAverage = voteAverage,
        voteCount = voteCount,
        originCountry = null,
    )
}
