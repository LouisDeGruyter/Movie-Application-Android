package com.example.moviesandseries.data.database.db.series

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviesandseries.data.database.converters.GenreConverter
import com.example.moviesandseries.data.database.converters.series.SeasonConverter
import com.example.moviesandseries.data.database.db.DbGenre
import com.example.moviesandseries.data.database.db.DbProductionCompany
import com.example.moviesandseries.data.database.db.DbProductionCountry
import com.example.moviesandseries.data.database.db.DbSpokenLanguage
import com.example.moviesandseries.data.database.db.asDbObject
import com.example.moviesandseries.data.database.db.asDomainObject
import com.example.moviesandseries.domain.series.Series

/**
 * Represents a database entity for the "series" table, storing information about TV series.
 *
 * @property adult Indicates if the series is intended for adult audiences.
 * @property backdropPath The path to the backdrop image associated with the series.
 * @property createdBy List of individuals who created the series.
 * @property episodeRunTime List of runtimes of episodes in the series.
 * @property firstAirDate The date when the series first aired.
 * @property genres List of genres associated with the series.
 * @property homepage The homepage of the series.
 * @property id The unique identifier for the series (primary key).
 * @property inProduction Indicates if the series is currently in production.
 * @property languages List of languages used in the series.
 * @property lastAirDate The date when the series last aired.
 * @property lastEpisodeToAir Details about the last episode that aired in the series.
 * @property name The name of the series.
 * @property networks List of networks associated with the series.
 * @property nextEpisodeToAir Details about the next episode to air in the series.
 * @property numberOfEpisodes The total number of episodes in the series.
 * @property numberOfSeasons The total number of seasons in the series.
 * @property originCountry List of countries where the series originated.
 * @property originalLanguage The original language of the series.
 * @property originalName The original name of the series.
 * @property overview An overview or summary of the series.
 * @property popularity The popularity score of the series.
 * @property posterPath The path to the poster image associated with the series.
 * @property productionCompanies List of production companies associated with the series.
 * @property productionCountries List of production countries associated with the series.
 * @property seasons List of seasons in the series.
 * @property spokenLanguages List of spoken languages in the series.
 * @property status The current status of the series (e.g., "In Production").
 * @property tagline A tagline or catchphrase associated with the series.
 * @property type The type of the series (e.g., "Scripted").
 * @property voteAverage The average vote rating for the series.
 * @property voteCount The total number of votes received by the series.
 * @property isFavorite Indicates if the series is marked as a favorite.
 */
@Entity(tableName = "series")
@TypeConverters(GenreConverter::class, SeasonConverter::class)
data class DbSeries(
    var adult: Boolean = false,
    var backdropPath: String = "",
    var createdBy: List<DbCreatedBy> = listOf(),
    var episodeRunTime: List<Int> = listOf(),
    var firstAirDate: String = "",
    var genres: List<DbGenre> = listOf(),
    var homepage: String = "",
    @PrimaryKey
    var id: Int = 0,
    var inProduction: Boolean = false,
    var languages: List<String> = listOf(),
    var lastAirDate: String = "",
    var lastEpisodeToAir: DbEpisode = DbEpisode(),
    var name: String = "",
    var networks: List<DbNetwork> = listOf(),
    var nextEpisodeToAir: DbEpisode = DbEpisode(),
    var numberOfEpisodes: Int = 0,
    var numberOfSeasons: Int = 0,
    var originCountry: List<String> = listOf(),
    var originalLanguage: String = "",
    var originalName: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var posterPath: String = "",
    var productionCompanies: List<DbProductionCompany> = listOf(),
    var productionCountries: List<DbProductionCountry> = listOf(),
    var seasons: List<DbSeason> = listOf(),
    var spokenLanguages: List<DbSpokenLanguage> = listOf(),
    var status: String = "",
    var tagline: String = "",
    var type: String = "",
    var voteAverage: Double = 0.0,
    var voteCount: Int = 0,
    var isFavorite: Boolean = false,
)

/**
 * Extension function to convert a [Series] domain object to a [DbSeries] database object.
 *
 * @return The corresponding [DbSeries] object.
 */
fun Series.asDbObject(): DbSeries = DbSeries(
    adult = this.adult,
    backdropPath = this.backdropPath,
    createdBy = this.createdBy.map { it.asDbObject() },
    episodeRunTime = this.episodeRunTime,
    firstAirDate = this.firstAirDate,
    genres = this.genres.map { it.asDbObject() },
    homepage = this.homepage,
    id = this.id,
    inProduction = this.inProduction,
    languages = this.languages,
    lastAirDate = this.lastAirDate,
    lastEpisodeToAir = this.lastEpisodeToAir.asDbObject(),
    name = this.name,
    networks = this.networks.map { it.asDbObject() },
    nextEpisodeToAir = this.nextEpisodeToAir.asDbObject(),
    numberOfEpisodes = this.numberOfEpisodes,
    numberOfSeasons = this.numberOfSeasons,
    originCountry = this.originCountry,
    originalLanguage = this.originalLanguage,
    originalName = this.originalName,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    productionCompanies = this.productionCompanies.map { it.asDbObject() },
    productionCountries = this.productionCountries.map { it.asDbObject() },
    seasons = this.seasons.map { it.asDbObject() },
    spokenLanguages = this.spokenLanguages.map { it.asDbObject() },
    status = this.status,
    tagline = this.tagline,
    type = this.type,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
    isFavorite = this.isFavorite,
)

/**
 * Extension function to convert a [DbSeries] database object to a [Series] domain object.
 *
 * @return The corresponding [Series] object.
 */
fun DbSeries.asDomainObject(): Series = Series(
    adult = this.adult,
    backdropPath = this.backdropPath,
    createdBy = this.createdBy.map { it.asDomainObject() },
    episodeRunTime = this.episodeRunTime,
    firstAirDate = this.firstAirDate,
    genres = this.genres.map { it.asDomainObject() },
    homepage = this.homepage,
    id = this.id,
    inProduction = this.inProduction,
    languages = this.languages,
    lastAirDate = this.lastAirDate,
    lastEpisodeToAir = this.lastEpisodeToAir.asDomainObject(),
    name = this.name,
    networks = this.networks.map { it.asDomainObject() },
    nextEpisodeToAir = this.nextEpisodeToAir.asDomainObject(),
    numberOfEpisodes = this.numberOfEpisodes,
    numberOfSeasons = this.numberOfSeasons,
    originCountry = this.originCountry,
    originalLanguage = this.originalLanguage,
    originalName = this.originalName,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    productionCompanies = this.productionCompanies.map { it.asDomainObject() },
    productionCountries = this.productionCountries.map { it.asDomainObject() },
    seasons = this.seasons.map { it.asDomainObject() },
    spokenLanguages = this.spokenLanguages.map { it.asDomainObject() },
    status = this.status,
    tagline = this.tagline,
    type = this.type,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
    isFavorite = this.isFavorite,
)

/**
 * Extension function to convert a list of [DbSeries] database objects to a list of [Series] domain objects.
 *
 * @return The corresponding list of [Series] objects.
 */
fun List<DbSeries>.asDomainObject() = this.map { it.asDomainObject() }
