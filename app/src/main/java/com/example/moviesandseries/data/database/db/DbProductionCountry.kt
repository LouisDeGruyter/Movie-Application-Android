package com.example.moviesandseries.data.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.ProductionCountry

@Entity(tableName = "production_countries")
data class DbProductionCountry(
    var iso3166_1: String = "",
    @PrimaryKey
    var name: String = ""
)

fun ProductionCountry.asDbObject(): DbProductionCountry = DbProductionCountry(
    iso3166_1 = this.iso3166_1,
    name = this.name
)

fun DbProductionCountry.asDomainObject(): ProductionCountry = ProductionCountry(
    iso3166_1 = this.iso3166_1,
    name = this.name
)

fun List<DbProductionCountry>.asDomainObject() = this.map { it.asDomainObject() }
