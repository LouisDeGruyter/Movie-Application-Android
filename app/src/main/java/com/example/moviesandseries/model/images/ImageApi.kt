package com.example.moviesandseries.model.images

import com.example.moviesandseries.domain.images.Image
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageApi(
    @Json(name = "aspect_ratio")
    val aspectRatio: Double,
    @Json(name = "file_path")
    val filePath: String,
    @Json(name = "height")
    val height: Int,
    @Json(name = "iso_639_1")
    val iso6391: String?,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int,
    @Json(name = "width")
    val width: Int,
)
fun ImageApi.asDomainObject(): Image {
    return Image(
        aspectRatio = aspectRatio,
        filePath = filePath,
        height = height,
        iso6391 = iso6391,
        voteAverage = voteAverage,
        voteCount = voteCount,
        width = width,
    )
}