package com.example.moviesandseries.model.collection

import com.example.moviesandseries.domain.Collection.CollectionIndex
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
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
fun CollectionIndexApi.asDomainObject(): CollectionIndex {
    return CollectionIndex(
        backdropPath = backdropPath ?: "",
        id = id,
        name = name,
        posterPath = posterPath ?: "",
    )
}
