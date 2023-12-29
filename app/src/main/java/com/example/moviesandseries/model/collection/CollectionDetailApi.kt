package com.example.moviesandseries.model.collection

import com.example.moviesandseries.model.movie.MovieIndexApi
import com.example.moviesandseries.model.movie.asDomainObject
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.example.moviesandseries.domain.Collection.CollectionDetail

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

fun CollectionDetailApi.asDomainObject(): CollectionDetail {
    return CollectionDetail(
        backdropPath = backdropPath,
        id = id,
        name = name,
        overview = overview,
        parts = parts.map { it.asDomainObject() },
        posterPath = posterPath,
    )
}
