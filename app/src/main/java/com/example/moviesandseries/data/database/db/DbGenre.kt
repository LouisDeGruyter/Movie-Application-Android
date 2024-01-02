package com.example.moviesandseries.data.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.Genre

/**
 * Represents a database entity for the "genres" table, storing information about genres.
 *
 * @property id The unique identifier for the genre (primary key).
 * @property name The name of the genre.
 */
@Entity(tableName = "genres")
data class DbGenre(
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
)

/**
 * Extension function to convert a [Genre] domain object to a [DbGenre] database object.
 *
 * @return The corresponding [DbGenre] object.
 */
fun Genre.asDbObject(): DbGenre = DbGenre(
    id = this.id,
    name = this.name,
)

/**
 * Extension function to convert a [DbGenre] database object to a [Genre] domain object.
 *
 * @return The corresponding [Genre] object.
 */
fun DbGenre.asDomainObject(): Genre = Genre(
    id = this.id,
    name = this.name,
)

/**
 * Extension function to convert a list of [DbGenre] database objects to a list of [Genre] domain objects.
 *
 * @return The corresponding list of [Genre] objects.
 */
fun List<DbGenre>.asDomainObject() = this.map { it.asDomainObject() }
