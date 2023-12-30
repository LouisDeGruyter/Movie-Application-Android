package com.example.moviesandseries.data.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviesandseries.data.database.converters.MovieConverter
import com.example.moviesandseries.domain.Collection

@Entity(tableName = "collections")
@TypeConverters(MovieConverter::class)
data class DbCollection(
    var backdropPath: String = "",
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var overview: String = "",
    var posterPath: String = ""
)

fun Collection.asDbObject(): DbCollection = DbCollection(
    backdropPath = this.backdropPath,
    id = this.id,
    name = this.name,
    overview = this.overview,
    posterPath = this.posterPath
)

fun DbCollection.asDomainObject(): Collection =
    Collection(
        backdropPath = backdropPath,
        id = id,
        name = name,
        overview = overview,
        parts = listOf(),
        posterPath = posterPath
    )

fun List<DbCollection>.asDomainObject() = this.map { it.asDomainObject() }

