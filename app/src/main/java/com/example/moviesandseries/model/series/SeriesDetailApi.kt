package com.example.moviesandseries.model.series

import com.example.moviesandseries.domain.Genre
import com.example.moviesandseries.domain.series.CreatedBy
import com.example.moviesandseries.domain.series.Network
import com.example.moviesandseries.domain.series.Series
import com.example.moviesandseries.domain.series.Episode
import com.example.moviesandseries.model.GenreApi
import com.example.moviesandseries.model.ProductionCompanyApi
import com.example.moviesandseries.model.ProductionCountryApi
import com.example.moviesandseries.model.SpokenLanguageApi
import com.example.moviesandseries.model.asDomainObject
import com.example.moviesandseries.model.series.episode.EpisodeApi
import com.example.moviesandseries.model.series.episode.asDomainObject
import com.example.moviesandseries.model.series.season.SeasonApi
import com.example.moviesandseries.model.series.season.asDomainObject
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of detailed information about a TV series.
 *
 * @property adult Indicates if the series is intended for an adult audience.
 * @property backdropPath The path to the backdrop image of the series.
 * @property createdBy List of creators or contributors to the series.
 * @property episodeRunTime List of runtimes of episodes in the series.
 * @property firstAirDate The first air date of the series.
 * @property genres List of genres associated with the series.
 * @property homepage The homepage of the series.
 * @property id The unique identifier of the series.
 * @property inProduction Indicates if the series is currently in production.
 * @property languages List of languages used in the series.
 * @property lastAirDate The last air date of the series.
 * @property lastEpisodeToAir The details of the last episode aired.
 * @property name The name of the series.
 * @property networks List of networks associated with the series.
 * @property nextEpisodeToAir The details of the next episode to air.
 * @property numberOfEpisodes The total number of episodes in the series.
 * @property numberOfSeasons The total number of seasons in the series.
 * @property originCountry List of origin countries for the series.
 * @property originalLanguage The original language of the series.
 * @property originalName The original name of the series.
 * @property overview A brief overview or synopsis of the series.
 * @property popularity The popularity score of the series.
 * @property posterPath The path to the poster image of the series.
 * @property productionCompanies List of production companies involved in the series.
 * @property productionCountries List of production countries for the series.
 * @property seasons List of seasons in the series.
 * @property spokenLanguages List of spoken languages in the series.
 * @property status The status of the series.
 * @property tagline The tagline of the series.
 * @property type The type of the series.
 * @property voteAverage The average vote score of the series.
 * @property voteCount The total number of votes received by the series.
 */
@JsonClass(generateAdapter = true)
data class SeriesDetailApi(
    @Json(name = "adult")
    val adult: Boolean?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "created_by")
    val createdBy: List<CreatedByApi?>,
    @Json(name = "episode_run_time")
    val episodeRunTime: List<Int>,
    @Json(name = "first_air_date")
    val firstAirDate: String?,
    @Json(name = "genres")
    val genres: List<GenreApi?>,
    @Json(name = "homepage")
    val homepage: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "in_production")
    val inProduction: Boolean,
    @Json(name = "languages")
    val languages: List<String?>,
    @Json(name = "last_air_date")
    val lastAirDate: String?,
    @Json(name = "last_episode_to_air")
    val lastEpisodeToAir: EpisodeApi?,
    @Json(name = "name")
    val name: String,
    @Json(name = "networks")
    val networks: List<NetworkApi?>,
    @Json(name = "next_episode_to_air")
    val nextEpisodeToAir: EpisodeApi?,
    @Json(name = "number_of_episodes")
    val numberOfEpisodes: Int,
    @Json(name = "number_of_seasons")
    val numberOfSeasons: Int,
    @Json(name = "origin_country")
    val originCountry: List<String?>,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "original_name")
    val originalName: String?,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompanyApi>,
    @Json(name = "production_countries")
    val productionCountries: List<ProductionCountryApi>,
    @Json(name = "seasons")
    val seasons: List<SeasonApi>,
    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguageApi>,
    @Json(name = "status")
    val status: String,
    @Json(name = "tagline")
    val tagline: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int,
)

/**
 * Extension function to convert [SeriesDetailApi] to [Series] domain object.
 */
fun SeriesDetailApi.asDomainObject(): Series {
    return Series(
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        createdBy = createdBy.map { it?.asDomainObject() ?: CreatedBy() },
        episodeRunTime = episodeRunTime,
        firstAirDate = firstAirDate ?: "",
        genres = genres.map { it?.asDomainObject() ?: Genre() },
        homepage = homepage ?: "",
        id = id,
        inProduction = inProduction,
        languages = languages.map { it ?: "" },
        lastAirDate = lastAirDate ?: "",
        lastEpisodeToAir = lastEpisodeToAir?.asDomainObject() ?: Episode(),
        name = name,
        networks = networks.map { it?.asDomainObject() ?: Network() },
        nextEpisodeToAir = nextEpisodeToAir?.asDomainObject() ?: Episode(),
        numberOfEpisodes = numberOfEpisodes,
        numberOfSeasons = numberOfSeasons,
        originCountry = originCountry.map { it ?: "" },
        originalLanguage = originalLanguage ?: "",
        originalName = originalName ?: "",
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        productionCompanies = productionCompanies.map { it.asDomainObject() },
        productionCountries = productionCountries.map { it.asDomainObject() },
        seasons = seasons.map { it.asDomainObject() },
        spokenLanguages = spokenLanguages.map { it.asDomainObject() },
        status = status,
        tagline = tagline,
        type = type,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
}
