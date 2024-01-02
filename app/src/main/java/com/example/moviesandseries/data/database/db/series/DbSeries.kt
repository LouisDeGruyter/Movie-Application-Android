package com.example.moviesandseries.data.database.db.series

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviesandseries.data.database.converters.GenreConverter
import com.example.moviesandseries.data.database.converters.movie.CollectionConverter
import com.example.moviesandseries.data.database.converters.series.SeasonConverter
import com.example.moviesandseries.data.database.db.DbGenre
import com.example.moviesandseries.data.database.db.DbProductionCompany
import com.example.moviesandseries.data.database.db.DbProductionCountry
import com.example.moviesandseries.data.database.db.DbSpokenLanguage
import com.example.moviesandseries.data.database.db.asDbObject
import com.example.moviesandseries.data.database.db.asDomainObject
import com.example.moviesandseries.domain.series.Series

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

fun List<DbSeries>.asDomainObject() = this.map { it.asDomainObject() }
