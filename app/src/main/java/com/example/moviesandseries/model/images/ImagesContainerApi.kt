package com.example.moviesandseries.model.images

import com.example.moviesandseries.domain.images.ImagesContainer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a container for images associated with a movie or series.
 *
 * @property backdrops List of backdrop images.
 * @property id The identifier of the container.
 * @property logos List of logo images.
 * @property posters List of poster images.
 */
@JsonClass(generateAdapter = true)
data class ImagesContainerApi(
    @Json(name = "backdrops")
    val backdrops: List<ImageApi>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "logos")
    val logos: List<ImageApi>,
    @Json(name = "posters")
    val posters: List<ImageApi>,
)

/**
 * Extension function to convert [ImagesContainerApi] to [ImagesContainer] domain object.
 */
fun ImagesContainerApi.asDomainObject(): ImagesContainer {
    return ImagesContainer(
        backdrops = backdrops.map { it.asDomainObject() },
        id = id,
        logos = logos.map { it.asDomainObject() },
        posters = posters.map { it.asDomainObject() },
    )
}
