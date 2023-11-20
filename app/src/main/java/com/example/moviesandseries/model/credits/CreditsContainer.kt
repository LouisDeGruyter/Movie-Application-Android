package com.example.moviesandseries.model.credits


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditsContainer(
    @Json(name = "cast")
    val cast: List<Cast?>,
    @Json(name = "crew")
    val crew: List<Crew?>,
    @Json(name = "id")
    val id: Int
)