package com.example.moviesandseries.model.videos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoContainer(
    var id: Int = 0,
    var results: List<Video> = listOf(),
)
