package com.example.moviesandseries.data.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesandseries.domain.SpokenLanguage

/**
 * Represents a database entity for the "spoken_languages" table, storing information about spoken languages.
 *
 * @property iso639_1 The ISO 639-1 code for the spoken language.
 * @property englishName The English name of the spoken language.
 * @property name The name of the spoken language (primary key).
 */
@Entity(tableName = "spoken_languages")
data class DbSpokenLanguage(
    var iso639_1: String = "",
    var englishName: String = "",
    @PrimaryKey
    var name: String = ""
)

/**
 * Extension function to convert a [SpokenLanguage] domain object to a [DbSpokenLanguage] database object.
 *
 * @return The corresponding [DbSpokenLanguage] object.
 */
fun SpokenLanguage.asDbObject(): DbSpokenLanguage = DbSpokenLanguage(
    iso639_1 = this.iso639_1,
    englishName = this.englishName,
    name = this.name
)

/**
 * Extension function to convert a [DbSpokenLanguage] database object to a [SpokenLanguage] domain object.
 *
 * @return The corresponding [SpokenLanguage] object.
 */
fun DbSpokenLanguage.asDomainObject(): SpokenLanguage = SpokenLanguage(
    iso639_1 = this.iso639_1,
    englishName = this.englishName,
    name = this.name
)

/**
 * Extension function to convert a list of [DbSpokenLanguage] database objects to a list of [SpokenLanguage] domain objects.
 *
 * @return The corresponding list of [SpokenLanguage] objects.
 */
fun List<DbSpokenLanguage>.asDomainObject() = this.map { it.asDomainObject() }
