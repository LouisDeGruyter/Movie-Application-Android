package com.example.moviesandseries.data.database.db.series

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.series.Episode

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
    // Assuming Credit is defined similarly to the previous examples
    val crew: List<DbCredit> = listOf(),
    val episodeType: String = "",
    val guestStars: List<DbCredit> = listOf(),
)

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

fun List<DbEpisode>.asDomainObject() = this.map { it.asDomainObject() }
