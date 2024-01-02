package com.example.moviesandseries.data.database.db.series

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.series.CreatedBy

@Entity(tableName = "created_by")
data class DbCreatedBy(
    var creditId: String = "",
    var gender: Int = 0,
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var profilePath: String = "",
)

fun CreatedBy.asDbObject(): DbCreatedBy = DbCreatedBy(
    creditId = this.creditId,
    gender = this.gender,
    id = this.id,
    name = this.name,
    profilePath = this.profilePath,
)

fun DbCreatedBy.asDomainObject(): CreatedBy = CreatedBy(
    creditId = this.creditId,
    gender = this.gender,
    id = this.id,
    name = this.name,
    profilePath = this.profilePath,
)

fun List<DbCreatedBy>.asDomainObject() = this.map { it.asDomainObject() }
