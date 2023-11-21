package com.example.moviesandseries.model


import com.example.moviesandseries.domain.Genre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreApi(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)
fun GenreApi.asDomainObject(): Genre {
    return Genre(
        id = id,
        name = name,
    )
}
