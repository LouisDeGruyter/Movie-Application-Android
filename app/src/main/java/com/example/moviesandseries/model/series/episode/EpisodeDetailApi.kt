package com.example.moviesandseries.model.series.episode

import com.example.moviesandseries.domain.series.Episode
import com.example.moviesandseries.model.credits.CreditApi
import com.example.moviesandseries.model.credits.asDomainObject
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of detailed information about a TV series episode.
 *
 * @property airDate The air date of the episode.
 * @property crew List of crew members associated with the episode.
 * @property episodeNumber The episode number.
 * @property episodeType The type of episode.
 * @property guestStars List of guest stars in the episode.
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
data class EpisodeDetailApi(
    @Json(name = "air_date")
    var airDate: String = "",
    @Json(name = "crew")
    var crew: List<CreditApi> = listOf(),
    @Json(name = "episode_number")
    var episodeNumber: Int = 0,
    @Json(name = "episode_type")
    var episodeType: String = "",
    @Json(name = "guest_stars")
    var guestStars: List<CreditApi> = listOf(),
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "name")
    var name: String = "",
    @Json(name = "overview")
    var overview: String = "",
    @Json(name = "production_code")
    var productionCode: String = "",
    @Json(name = "runtime")
    var runtime: Int = 0,
    @Json(name = "season_number")
    var seasonNumber: Int = 0,
    @Json(name = "show_id")
    var showId: Int = 0,
    @Json(name = "still_path")
    var stillPath: String? = "",
    @Json(name = "vote_average")
    var voteAverage: Double = 0.0,
    @Json(name = "vote_count")
    var voteCount: Int = 0,
)

/**
 * Extension function to convert [EpisodeDetailApi] to [Episode] domain object.
 */
fun EpisodeDetailApi.asDomainObject(): Episode {
    return Episode(
        airDate = airDate,
        crew = crew.map { it.asDomainObject() },
        episodeNumber = episodeNumber,
        episodeType = episodeType,
        guestStars = guestStars.map { it.asDomainObject() },
        id = id,
        name = name,
        overview = overview,
        productionCode = productionCode,
        runtime = runtime,
        seasonNumber = seasonNumber,
        showId = showId,
        stillPath = stillPath ?: "",
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
}
