package com.example.moviesandseries.data.database.db.series

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.series.Season

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

fun List<DbSeason>.asDomainObject() = this.map { it.asDomainObject() }
