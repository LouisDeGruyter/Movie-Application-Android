package com.example.moviesandseries.model.images

import com.example.moviesandseries.domain.images.Image
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of an image associated with a movie or series.
 *
 * @property aspectRatio The aspect ratio of the image.
 * @property filePath The file path to the image.
 * @property height The height of the image in pixels.
 * @property iso6391 The ISO 639-1 language code of the image. (Nullable)
 * @property voteAverage The average vote for the image.
 * @property voteCount The total number of votes for the image.
 * @property width The width of the image in pixels.
 */
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

/**
 * Extension function to convert [ImageApi] to [Image] domain object.
 */
fun ImageApi.asDomainObject(): Image {
    return Image(
        aspectRatio = aspectRatio,
        filePath = filePath,
        height = height,
        iso6391 = iso6391 ?: "",
        voteAverage = voteAverage,
        voteCount = voteCount,
        width = width,
    )
}
