package com.example.moviesandseries.model.series

import com.example.moviesandseries.domain.Genre
import com.example.moviesandseries.domain.series.Series
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of basic information about a TV series used in series lists.
 *
 * @property backdropPath The path to the backdrop image of the series.
 * @property firstAirDate The first air date of the series.
 * @property genreIds List of genre IDs associated with the series.
 * @property id The unique identifier of the series.
 * @property name The name of the series.
 * @property originCountry List of origin countries for the series.
 * @property originalLanguage The original language of the series.
 * @property originalName The original name of the series.
 * @property overview A brief overview or synopsis of the series.
 * @property popularity The popularity score of the series.
 * @property posterPath The path to the poster image of the series.
 * @property voteAverage The average vote score of the series.
 * @property voteCount The total number of votes received by the series.
 */
@JsonClass(generateAdapter = true)
data class SeriesIndexApi(
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "first_air_date")
    val firstAirDate: String?,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "origin_country")
    val originCountry: List<String>,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_name")
    val originalName: String?,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int,
)

/**
 * Extension function to convert [SeriesIndexApi] to [Series] domain object.
 */
fun SeriesIndexApi.asDomainObject(): Series {
    return Series(
        backdropPath = backdropPath ?: "",
        firstAirDate = firstAirDate ?: "",
        genres = genreIds.map { Genre(id = it) },
        id = id,
        name = name,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalName = originalName ?: "",
        overview = overview,
        popularity = popularity,
        posterPath = posterPath ?: "",
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
}
