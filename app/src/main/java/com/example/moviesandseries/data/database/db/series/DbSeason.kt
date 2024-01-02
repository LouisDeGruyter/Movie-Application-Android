package com.example.moviesandseries.data.database.db.series

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.series.Season

/**
 * Represents a database entity for the "seasons" table, storing information about series seasons.
 *
 * @property id The unique identifier for the season (primary key).
 * @property airDate The air date of the season.
 * @property episodeCount The number of episodes in the season.
 * @property name The name of the season.
 * @property overview An overview or summary of the season.
 * @property posterPath The path to the poster associated with the season.
 * @property seasonNumber The season number.
 * @property voteAverage The average vote rating for the season.
 */
@Entity(tableName = "seasons")
data class DbSeason(
    @PrimaryKey
    var id: Int = 0,
    var airDate: String = "",
    var episodeCount: Int = 0,
    var name: String = "",
    var overview: String = "",
    var posterPath: String = "",
    var seasonNumber: Int = 0,
    var voteAverage: Double = 0.0
)

/**
 * Extension function to convert a [Season] domain object to a [DbSeason] database object.
 *
 * @return The corresponding [DbSeason] object.
 */
fun Season.asDbObject(): DbSeason = DbSeason(
    id = this.id,
    airDate = this.airDate,
    episodeCount = this.episodeCount,
    name = this.name,
    overview = this.overview,
    posterPath = this.posterPath,
    seasonNumber = this.seasonNumber,
    voteAverage = this.voteAverage
)

/**
 * Extension function to convert a [DbSeason] database object to a [Season] domain object.
 *
 * @return The corresponding [Season] object.
 */
fun DbSeason.asDomainObject(): Season = Season(
    id = this.id,
    airDate = this.airDate,
    episodeCount = this.episodeCount,
    name = this.name,
    overview = this.overview,
    posterPath = this.posterPath,
    seasonNumber = this.seasonNumber,
    voteAverage = this.voteAverage
)

/**
 * Extension function to convert a list of [DbSeason] database objects to a list of [Season] domain objects.
 *
 * @return The corresponding list of [Season] objects.
 */
fun List<DbSeason>.asDomainObject() = this.map { it.asDomainObject() }
