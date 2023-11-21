package com.example.moviesandseries.model

import com.example.moviesandseries.domain.SpokenLanguage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpokenLanguageApi(
    @Json(name = "english_name")
    val englishName: String,
    @Json(name = "iso_639_1")
    val iso6391: String,
    @Json(name = "name")
    val name: String,
)
fun SpokenLanguageApi.asDomainObject(): SpokenLanguage {
    return SpokenLanguage(
        englishName = englishName,
        iso639_1 = iso6391,
        name = name,
    )
}
