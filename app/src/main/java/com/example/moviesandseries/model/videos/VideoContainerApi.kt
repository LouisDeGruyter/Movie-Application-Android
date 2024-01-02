package com.example.moviesandseries.model.videos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a container for a collection of videos associated with a movie or series.
 *
 * @property id The unique identifier of the video container.
 * @property results List of [VideoApi] objects representing individual videos.
 */
@JsonClass(generateAdapter = true)
data class VideoContainerApi(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "results")
    var results: List<VideoApi> = listOf(),
)

/**
 * Extension function to convert [VideoContainerApi] to [VideoContainer] domain object.
 */
fun VideoContainerApi.asDomainObject(): VideoContainer {
    return VideoContainer(
        id = id,
        results = results.map { it.asDomainObject() },
    )
}
