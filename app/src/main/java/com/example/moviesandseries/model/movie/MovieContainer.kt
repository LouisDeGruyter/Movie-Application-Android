package com.example.moviesandseries.model.movie


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieContainer(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<MovieIndex>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)