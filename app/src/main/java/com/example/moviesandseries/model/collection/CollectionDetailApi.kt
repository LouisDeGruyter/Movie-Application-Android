package com.example.moviesandseries.model.collection

import com.example.moviesandseries.model.movie.MovieIndexApi
import com.example.moviesandseries.model.movie.asDomainObject
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.example.moviesandseries.domain.Collection

/**
 * API representation of a collection with detailed information.
 *
 * @property backdropPath The backdrop path for the collection.
 * @property id The unique identifier of the collection.
 * @property name The name of the collection.
 * @property overview A brief overview or description of the collection.
 * @property parts The list of movies that belong to the collection.
 * @property posterPath The poster path for the collection.
 */
@JsonClass(generateAdapter = true)
data class CollectionDetailApi(
    @Json(name = "backdrop_path")
    var backdropPath: String = "",
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "name")
    var name: String = "",
    @Json(name = "overview")
    var overview: String = "",
    @Json(name = "parts")
    var parts: List<MovieIndexApi> = listOf(),
    @Json(name = "poster_path")
    var posterPath: String = "",
)

/**
 * Extension function to convert [CollectionDetailApi] to [Collection] domain object.
 */
fun CollectionDetailApi.asDomainObject(): Collection {
    return Collection(
        backdropPath = backdropPath,
        id = id,
        name = name,
        overview = overview,
        parts = parts.map { it.asDomainObject() },
        posterPath = posterPath,
    )
}
