package com.example.moviesandseries.model.videos

/**
 * Data class representing a container for a list of videos related to a movie or TV series.
 *
 * @property id The unique identifier of the video container.
 * @property results A list of [Video] objects containing information about each video.
 */
data class VideoContainer(
    var id: Int = 0,
    var results: List<Video> = listOf()
)
