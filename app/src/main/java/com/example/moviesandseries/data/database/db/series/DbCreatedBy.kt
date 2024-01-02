package com.example.moviesandseries.data.database.db.series

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.series.CreatedBy

/**
 * Represents a database entity for the "created_by" table, storing information about series creators.
 *
 * @property creditId The unique identifier for the credit associated with the creator.
 * @property gender The gender of the creator (0 for unknown, 1 for female, 2 for male).
 * @property id The unique identifier for the creator (primary key).
 * @property name The name of the creator.
 * @property profilePath The path to the profile image of the creator.
 */
@Entity(tableName = "created_by")
data class DbCreatedBy(
    var creditId: String = "",
    var gender: Int = 0,
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var profilePath: String = "",
)

/**
 * Extension function to convert a [CreatedBy] domain object to a [DbCreatedBy] database object.
 *
 * @return The corresponding [DbCreatedBy] object.
 */
fun CreatedBy.asDbObject(): DbCreatedBy = DbCreatedBy(
    creditId = this.creditId,
    gender = this.gender,
    id = this.id,
    name = this.name,
    profilePath = this.profilePath,
)

/**
 * Extension function to convert a [DbCreatedBy] database object to a [CreatedBy] domain object.
 *
 * @return The corresponding [CreatedBy] object.
 */
fun DbCreatedBy.asDomainObject(): CreatedBy = CreatedBy(
    creditId = this.creditId,
    gender = this.gender,
    id = this.id,
    name = this.name,
    profilePath = this.profilePath,
)

/**
 * Extension function to convert a list of [DbCreatedBy] database objects to a list of [CreatedBy] domain objects.
 *
 * @return The corresponding list of [CreatedBy] objects.
 */
fun List<DbCreatedBy>.asDomainObject() = this.map { it.asDomainObject() }
