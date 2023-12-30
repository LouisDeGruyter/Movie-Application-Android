package com.example.moviesandseries.model.videos

data class VideoContainer(
    var id: Int = 0,
    var results: List<Video> = listOf(),
)
