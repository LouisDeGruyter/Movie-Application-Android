package com.example.moviesandseries.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagesContainer(
    @Json(name = "backdrops")
    val backdrops: List<Backdrop>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "logos")
    val logos: List<Logo>,
    @Json(name = "posters")
    val posters: List<Poster>
)