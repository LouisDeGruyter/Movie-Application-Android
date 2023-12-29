package com.example.moviesandseries.data.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.ProductionCompany

@Entity(tableName = "production_companies")
data class DbProductionCompany(
    @PrimaryKey
    var id: Int = 0,
    var logoPath: String = "",
    var name: String = "",
    var originCountry: String = ""
)

fun ProductionCompany.asDbObject(): DbProductionCompany = DbProductionCompany(
    id = this.id,
    logoPath = this.logoPath,
    name = this.name,
    originCountry = this.originCountry
)

fun DbProductionCompany.asDomainObject(): ProductionCompany = ProductionCompany(
    id = this.id,
    logoPath = this.logoPath,
    name = this.name,
    originCountry = this.originCountry
)

fun List<DbProductionCompany>.asDomainObject() = this.map { it.asDomainObject() }
