package com.example.moviesandseries.model.videos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoContainerApi(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "results")
    var results: List<VideoApi> = listOf(),
)
fun VideoContainerApi.asDomainObject(): VideoContainer {
    return VideoContainer(
        id = id,
        results = results.map { it.asDomainObject() },
    )
}
