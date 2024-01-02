package com.example.moviesandseries.data.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.ProductionCountry

/**
 * Represents a database entity for the "production_countries" table, storing information about production countries.
 *
 * @property iso3166_1 The ISO 3166-1 alpha-2 code for the production country.
 * @property name The name of the production country (primary key).
 */
@Entity(tableName = "production_countries")
data class DbProductionCountry(
    var iso3166_1: String = "",
    @PrimaryKey
    var name: String = ""
)

/**
 * Extension function to convert a [ProductionCountry] domain object to a [DbProductionCountry] database object.
 *
 * @return The corresponding [DbProductionCountry] object.
 */
fun ProductionCountry.asDbObject(): DbProductionCountry = DbProductionCountry(
    iso3166_1 = this.iso3166_1,
    name = this.name
)

/**
 * Extension function to convert a [DbProductionCountry] database object to a [ProductionCountry] domain object.
 *
 * @return The corresponding [ProductionCountry] object.
 */
fun DbProductionCountry.asDomainObject(): ProductionCountry = ProductionCountry(
    iso3166_1 = this.iso3166_1,
    name = this.name
)

/**
 * Extension function to convert a list of [DbProductionCountry] database objects to a list of [ProductionCountry] domain objects.
 *
 * @return The corresponding list of [ProductionCountry] objects.
 */
fun List<DbProductionCountry>.asDomainObject() = this.map { it.asDomainObject() }
