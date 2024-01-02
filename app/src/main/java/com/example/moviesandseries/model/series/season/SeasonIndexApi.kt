package com.example.moviesandseries.model.series.season


import com.example.moviesandseries.domain.series.Season
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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
