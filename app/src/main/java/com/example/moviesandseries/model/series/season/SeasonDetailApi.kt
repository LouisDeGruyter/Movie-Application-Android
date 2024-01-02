package com.example.moviesandseries.model.series.season

import com.example.moviesandseries.domain.series.Season
import com.example.moviesandseries.model.series.episode.EpisodeDetailApi
import com.example.moviesandseries.model.series.episode.asDomainObject
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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
