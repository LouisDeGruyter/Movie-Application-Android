package com.example.moviesandseries.model.series.season

import com.example.moviesandseries.domain.series.Season
import com.example.moviesandseries.model.series.episode.EpisodeDetailApi
import com.example.moviesandseries.model.series.episode.asDomainObject
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of detailed information about a TV series season.
 *
 * @property airDate The air date of the season.
 * @property episodes List of episodes in the season.
 * @property _id The unique identifier of the season.
 * @property id The unique identifier of the season (an integer version of _id).
 * @property name The name of the season.
 * @property overview A brief overview or synopsis of the season.
 * @property posterPath The path to the poster image of the season.
 * @property seasonNumber The season number.
 * @property voteAverage The average vote score of the season.
 */
@JsonClass(generateAdapter = true)
data class SeasonDetailApi(
    @Json(name = "air_date")
    var airDate: String = "",
    @Json(name = "episodes")
    var episodes: List<EpisodeDetailApi> = listOf(),
    @Json(name = "_id")
    var _id: String = "",
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "name")
    var name: String = "",
    @Json(name = "overview")
    var overview: String = "",
    @Json(name = "poster_path")
    var posterPath: String = "",
    @Json(name = "season_number")
    var seasonNumber: Int = 0,
    @Json(name = "vote_average")
    var voteAverage: Double = 0.0,
)

/**
 * Extension function to convert [SeasonDetailApi] to [Season] domain object.
 */
fun SeasonDetailApi.asDomainObject(): Season {
    return Season(
        airDate = airDate,
        episodes = episodes.map { it.asDomainObject() },
        id = id,
        name = name,
        overview = overview,
        posterPath = posterPath,
        seasonNumber = seasonNumber,
        voteAverage = voteAverage,
    )
}
