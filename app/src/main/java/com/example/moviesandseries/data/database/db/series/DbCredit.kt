package com.example.moviesandseries.data.database.db.series

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.credits.Credit

/**
 * Represents a database entity for the "credits" table, storing information about series credits.
 *
 * @property adult Indicates if the credit is associated with an adult content.
 * @property castId The unique identifier for the cast associated with the credit.
 * @property character The character name associated with the credit.
 * @property creditId The unique identifier for the credit (primary key).
 * @property gender The gender of the person associated with the credit (0 for unknown, 1 for female, 2 for male).
 * @property id The unique identifier for the person associated with the credit.
 * @property knownForDepartment The department for which the person is known for their work.
 * @property name The name of the person associated with the credit.
 * @property order The order in which the person appears in the cast.
 * @property originalName The original name of the person associated with the credit.
 * @property popularity The popularity score of the person associated with the credit.
 * @property profilePath The path to the profile image of the person associated with the credit.
 */
@Entity(tableName = "credits")
data class DbCredit(
    var adult: Boolean = false,
    var castId: Int = 0,
    var character: String = "",
    var creditId: String = "",
    var gender: Int = 0,
    @PrimaryKey
    var id: Int = 0,
    var knownForDepartment: String = "",
    var name: String = "",
    var order: Int = 0,
    var originalName: String = "",
    var popularity: Double = 0.0,
    var profilePath: String = ""
)

/**
 * Extension function to convert a [Credit] domain object to a [DbCredit] database object.
 *
 * @return The corresponding [DbCredit] object.
 */
fun Credit.asDbObject(): DbCredit = DbCredit(
    adult = this.adult,
    castId = this.castId,
    character = this.character,
    creditId = this.creditId,
    gender = this.gender,
    id = this.id,
    knownForDepartment = this.knownForDepartment,
    name = this.name,
    order = this.order,
    originalName = this.originalName,
    popularity = this.popularity,
    profilePath = this.profilePath
)

/**
 * Extension function to convert a [DbCredit] database object to a [Credit] domain object.
 *
 * @return The corresponding [Credit] object.
 */
fun DbCredit.asDomainObject(): Credit = Credit(
    adult = this.adult,
    castId = this.castId,
    character = this.character,
    creditId = this.creditId,
    gender = this.gender,
    id = this.id,
    knownForDepartment = this.knownForDepartment,
    name = this.name,
    order = this.order,
    originalName = this.originalName,
    popularity = this.popularity,
    profilePath = this.profilePath
)

/**
 * Extension function to convert a list of [DbCredit] database objects to a list of [Credit] domain objects.
 *
 * @return The corresponding list of [Credit] objects.
 */
fun List<DbCredit>.asDomainObject() = this.map { it.asDomainObject() }
