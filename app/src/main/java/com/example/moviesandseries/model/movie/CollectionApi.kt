package com.example.moviesandseries.model.movie


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.example.moviesandseries.domain.movie.Collection
@JsonClass(generateAdapter = true)
data class CollectionApi(
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "poster_path")
    val posterPath: String?
)
fun CollectionApi.asDomainObject(): Collection {
    return Collection(
        backdropPath = backdropPath,
        id = id,
        name = name,
        posterPath = posterPath,
    )
}
