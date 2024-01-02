package com.example.moviesandseries.model.series.episode

import com.example.moviesandseries.domain.series.Episode
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a TV series episode.
 *
 * @property airDate The air date of the episode.
 * @property episodeNumber The episode number.
 * @property id The unique identifier of the episode.
 * @property name The name of the episode.
 * @property overview A brief overview or synopsis of the episode.
 * @property productionCode The production code of the episode.
 * @property runtime The duration of the episode in minutes.
 * @property seasonNumber The season number to which the episode belongs.
 * @property showId The unique identifier of the TV show to which the episode belongs.
 * @property stillPath The path to the still image of the episode.
 * @property voteAverage The average vote score of the episode.
 * @property voteCount The total number of votes received by the episode.
 */
@JsonClass(generateAdapter = true)
data class EpisodeApi(
    @Json(name = "air_date")
    val airDate: String?,
    @Json(name = "episode_number")
    val episodeNumber: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "production_code")
    val productionCode: String?,
    @Json(name = "runtime")
    val runtime: Int?,
    @Json(name = "season_number")
    val seasonNumber: Int,
    @Json(name = "show_id")
    val showId: Int,
    @Json(name = "still_path")
    val stillPath: String?,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int,
)

/**
 * Extension function to convert [EpisodeApi] to [Episode] domain object.
 */
fun EpisodeApi.asDomainObject(): Episode {
    return Episode(
        airDate = airDate ?: "",
        episodeNumber = episodeNumber,
        id = id,
        name = name,
        overview = overview,
        productionCode = productionCode ?: "",
        runtime = runtime ?: 0,
        seasonNumber = seasonNumber,
        showId = showId,
        stillPath = stillPath ?: "",
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
}
