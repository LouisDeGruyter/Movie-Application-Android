package com.example.moviesandseries.data.database.db.series

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.credits.Credit

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

fun List<DbCredit>.asDomainObject() = this.map { it.asDomainObject() }
