package com.example.moviesandseries.model

import com.example.moviesandseries.domain.Genre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a movie or series genre.
 *
 * @property id The unique identifier of the genre.
 * @property name The name of the genre.
 */
@JsonClass(generateAdapter = true)
data class GenreApi(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)

/**
 * Extension function to convert [GenreApi] to [Genre] domain object.
 */
fun GenreApi.asDomainObject(): Genre {
    return Genre(
        id = id,
        name = name,
    )
}
