package com.example.moviesandseries.model.collection

import com.example.moviesandseries.domain.Collection
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a collection with basic information.
 *
 * @property backdropPath The backdrop path for the collection. Can be null.
 * @property id The unique identifier of the collection.
 * @property name The name of the collection.
 * @property posterPath The poster path for the collection. Can be null.
 */
@JsonClass(generateAdapter = true)
data class CollectionIndexApi(
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "poster_path")
    val posterPath: String?,
)

/**
 * Extension function to convert [CollectionIndexApi] to [Collection] domain object.
 */
fun CollectionIndexApi.asDomainObject(): Collection {
    return Collection(
        backdropPath = backdropPath ?: "",
        id = id,
        name = name,
        posterPath = posterPath ?: "",
    )
}
