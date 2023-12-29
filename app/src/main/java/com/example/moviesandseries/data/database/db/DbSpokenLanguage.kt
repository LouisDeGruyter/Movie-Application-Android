package com.example.moviesandseries.data.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.SpokenLanguage

@Entity(tableName = "spoken_languages")
data class DbSpokenLanguage(
    var iso639_1: String = "",
    var englishName: String = "",
    @PrimaryKey
    var name: String = ""
)

fun SpokenLanguage.asDbObject(): DbSpokenLanguage = DbSpokenLanguage(
    iso639_1 = this.iso639_1,
    englishName = this.englishName,
    name = this.name
)

fun DbSpokenLanguage.asDomainObject(): SpokenLanguage = SpokenLanguage(
    iso639_1 = this.iso639_1,
    englishName = this.englishName,
    name = this.name
)

fun List<DbSpokenLanguage>.asDomainObject() = this.map { it.asDomainObject() }
