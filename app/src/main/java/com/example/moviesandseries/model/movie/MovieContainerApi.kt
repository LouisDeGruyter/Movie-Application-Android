package com.example.moviesandseries.model.movie

import com.example.moviesandseries.domain.movie.MovieContainer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieContainerApi(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<MovieIndexApi>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int,
)

fun MovieContainerApi.asDomainObject(): MovieContainer {
    return MovieContainer(
        page = page,
        results = results.map { it.asDomainObject() },
        totalPages = totalPages,
        totalResults = totalResults,
    )
}
