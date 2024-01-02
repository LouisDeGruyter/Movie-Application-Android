package com.example.moviesandseries.model.series.season

import com.example.moviesandseries.domain.series.Season
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of basic information about a TV series season.
 *
 * @property airDate The air date of the season.
 * @property episodeCount The number of episodes in the season.
 * @property id The unique identifier of the season.
 * @property name The name of the season.
 * @property overview A brief overview or synopsis of the season.
 * @property posterPath The path to the poster image of the season.
 * @property seasonNumber The season number.
 * @property voteAverage The average vote score of the season.
 */
@JsonClass(generateAdapter = true)
data class SeasonApi(
    @Json(name = "air_date")
    val airDate: String?,
    @Json(name = "episode_count")
    val episodeCount: Int?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "season_number")
    val seasonNumber: Int,
    @Json(name = "vote_average")
    val voteAverage: Double
)

/**
 * Extension function to convert [SeasonApi] to [Season] domain object.
 */
fun SeasonApi.asDomainObject(): Season {
    return Season(
        airDate = airDate ?: "",
        episodeCount = episodeCount ?: 0,
        id = id,
        name = name,
        overview = overview,
        posterPath = posterPath ?: "",
        seasonNumber = seasonNumber,
        voteAverage = voteAverage,
    )
}
