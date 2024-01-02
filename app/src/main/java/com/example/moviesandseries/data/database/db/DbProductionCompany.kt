package com.example.moviesandseries.data.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.ProductionCompany

/**
 * Represents a database entity for the "production_companies" table, storing information about production companies.
 *
 * @property id The unique identifier for the production company (primary key).
 * @property logoPath The path to the logo of the production company.
 * @property name The name of the production company.
 * @property originCountry The country of origin for the production company.
 */
@Entity(tableName = "production_companies")
data class DbProductionCompany(
    @PrimaryKey
    var id: Int = 0,
    var logoPath: String = "",
    var name: String = "",
    var originCountry: String = ""
)

/**
 * Extension function to convert a [ProductionCompany] domain object to a [DbProductionCompany] database object.
 *
 * @return The corresponding [DbProductionCompany] object.
 */
fun ProductionCompany.asDbObject(): DbProductionCompany = DbProductionCompany(
    id = this.id,
    logoPath = this.logoPath,
    name = this.name,
    originCountry = this.originCountry
)

/**
 * Extension function to convert a [DbProductionCompany] database object to a [ProductionCompany] domain object.
 *
 * @return The corresponding [ProductionCompany] object.
 */
fun DbProductionCompany.asDomainObject(): ProductionCompany = ProductionCompany(
    id = this.id,
    logoPath = this.logoPath,
    name = this.name,
    originCountry = this.originCountry
)

/**
 * Extension function to convert a list of [DbProductionCompany] database objects to a list of [ProductionCompany] domain objects.
 *
 * @return The corresponding list of [ProductionCompany] objects.
 */
fun List<DbProductionCompany>.asDomainObject() = this.map { it.asDomainObject() }
