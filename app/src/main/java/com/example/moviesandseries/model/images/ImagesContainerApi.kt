package com.example.moviesandseries.model.images


import com.example.moviesandseries.domain.images.ImagesContainer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagesContainerApi(
    @Json(name = "backdrops")
    val backdrops: List<ImageApi>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "logos")
    val logos: List<ImageApi>,
    @Json(name = "posters")
    val posters: List<ImageApi>
)
fun ImagesContainerApi.asDomainObject(): ImagesContainer {
    return ImagesContainer(
        backdrops = backdrops.map { it.asDomainObject() },
        id = id,
        logos = logos.map { it.asDomainObject() },
        posters = posters.map { it.asDomainObject() }
    )
}
