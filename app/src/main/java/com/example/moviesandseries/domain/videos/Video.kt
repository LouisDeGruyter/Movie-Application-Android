package com.example.moviesandseries.model.videos

/**
 * Data class representing a video related to a movie or TV series.
 *
 * @property id The unique identifier of the video.
 * @property iso31661 The ISO 3166-1 alpha-2 country code where the video is available.
 * @property iso6391 The ISO 639-1 language code for the video.
 * @property key The key to access the video on its respective platform.
 * @property name The name or title of the video.
 * @property official A flag indicating whether the video is official or not.
 * @property publishedAt The date when the video was published.
 * @property site The platform or site where the video is hosted.
 * @property size The size or resolution of the video.
 * @property type The type or category of the video.
 */
data class Video(
    var id: String = "",
    var iso31661: String = "",
    var iso6391: String = "",
    var key: String = "",
    var name: String = "",
    var official: Boolean = false,
    var publishedAt: String = "",
    var site: String = "",
    var size: Int = 0,
    var type: String = ""
)
