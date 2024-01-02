package com.example.moviesandseries.model

import com.example.moviesandseries.domain.SpokenLanguage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a spoken language associated with a movie or series.
 *
 * @property englishName The English name of the spoken language.
 * @property iso6391 The ISO 639-1 code of the spoken language.
 * @property name The name of the spoken language.
 */
@JsonClass(generateAdapter = true)
data class SpokenLanguageApi(
    @Json(name = "english_name")
    val englishName: String,
    @Json(name = "iso_639_1")
    val iso6391: String,
    @Json(name = "name")
    val name: String,
)

/**
 * Extension function to convert [SpokenLanguageApi] to [SpokenLanguage] domain object.
 */
fun SpokenLanguageApi.asDomainObject(): SpokenLanguage {
    return SpokenLanguage(
        englishName = englishName,
        iso639_1 = iso6391,
        name = name,
    )
}
