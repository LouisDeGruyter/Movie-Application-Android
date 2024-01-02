package com.example.moviesandseries.data.database.db.movies

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.Collection

/**
 * Represents a database entity for collections associated with movies.
 *
 * @property backdropPath The backdrop path for the collection.
 * @property id The unique identifier for the collection (primary key).
 * @property name The name of the collection.
 * @property overview The overview or description of the collection.
 * @property posterPath The poster path for the collection.
 */
@Entity(tableName = "collections")
data class DbCollection(
    var backdropPath: String = "",
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var overview: String = "",
    var posterPath: String = ""
)

/**
 * Extension function to convert a [Collection] domain object to a [DbCollection] database object.
 *
 * @return The corresponding [DbCollection] object.
 */
fun Collection.asDbObject(): DbCollection = DbCollection(
    backdropPath = this.backdropPath,
    id = this.id,
    name = this.name,
    overview = this.overview,
    posterPath = this.posterPath
)

/**
 * Extension function to convert a [DbCollection] database object to a [Collection] domain object.
 *
 * @return The corresponding [Collection] object.
 */
fun DbCollection.asDomainObject(): Collection =
    Collection(
        backdropPath = backdropPath,
        id = id,
        name = name,
        overview = overview,
        parts = listOf(),
        posterPath = posterPath
    )

/**
 * Extension function to convert a list of [DbCollection] database objects to a list of [Collection] domain objects.
 *
 * @return The corresponding list of [Collection] objects.
 */
fun List<DbCollection>.asDomainObject() = this.map { it.asDomainObject() }
