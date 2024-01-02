package com.example.moviesandseries.data.database.db.series

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.series.Episode

/**
 * Represents a database entity for the "episodes" table, storing information about series episodes.
 *
 * @property airDate The air date of the episode.
 * @property episodeNumber The episode number.
 * @property id The unique identifier for the episode (primary key).
 * @property name The name of the episode.
 * @property overview The overview or summary of the episode.
 * @property productionCode The production code associated with the episode.
 * @property runtime The runtime duration of the episode in minutes.
 * @property seasonNumber The season number to which the episode belongs.
 * @property showId The unique identifier for the series to which the episode belongs.
 * @property stillPath The path to the still image associated with the episode.
 * @property voteAverage The average vote score for the episode.
 * @property voteCount The total vote count for the episode.
 * @property crew The list of crew members associated with the episode.
 * @property episodeType The type of the episode.
 * @property guestStars The list of guest stars appearing in the episode.
 */
@Entity(tableName = "episodes")
data class DbEpisode(
    var airDate: String = "",
    var episodeNumber: Int = 0,
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var overview: String = "",
    var productionCode: String = "",
    var runtime: Int = 0,
    var seasonNumber: Int = 0,
    var showId: Int = 0,
    var stillPath: String = "",
    var voteAverage: Double = 0.0,
    var voteCount: Int = 0,
    val crew: List<DbCredit> = listOf(),
    val episodeType: String = "",
    val guestStars: List<DbCredit> = listOf(),
)

/**
 * Extension function to convert an [Episode] domain object to a [DbEpisode] database object.
 *
 * @return The corresponding [DbEpisode] object.
 */
fun Episode.asDbObject(): DbEpisode = DbEpisode(
    airDate = this.airDate,
    episodeNumber = this.episodeNumber,
    id = this.id,
    name = this.name,
    overview = this.overview,
    productionCode = this.productionCode,
    runtime = this.runtime,
    seasonNumber = this.seasonNumber,
    showId = this.showId,
    stillPath = this.stillPath,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
    crew = this.crew.map { it.asDbObject() },
    episodeType = this.episodeType,
    guestStars = this.guestStars.map { it.asDbObject() },
)

/**
 * Extension function to convert a [DbEpisode] database object to an [Episode] domain object.
 *
 * @return The corresponding [Episode] object.
 */
fun DbEpisode.asDomainObject(): Episode = Episode(
    airDate = this.airDate,
    episodeNumber = this.episodeNumber,
    id = this.id,
    name = this.name,
    overview = this.overview,
    productionCode = this.productionCode,
    runtime = this.runtime,
    seasonNumber = this.seasonNumber,
    showId = this.showId,
    stillPath = this.stillPath,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
    crew = this.crew.map { it.asDomainObject() },
    episodeType = this.episodeType,
    guestStars = this.guestStars.map { it.asDomainObject() },
)

/**
 * Extension function to convert a list of [DbEpisode] database objects to a list of [Episode] domain objects.
 *
 * @return The corresponding list of [Episode] objects.
 */
fun List<DbEpisode>.asDomainObject() = this.map { it.asDomainObject() }
