package com.example.moviesandseries.data.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.Genre

@Entity(tableName = "genres")
data class DbGenre(
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
)

fun Genre.asDbObject(): DbGenre = DbGenre(
    id = this.id,
    name = this.name,
)

fun DbGenre.asDomainObject(): Genre = Genre(
    id = this.id,
    name = this.name,
)

fun List<DbGenre>.asDomainObject() = this.map { it.asDomainObject() }
