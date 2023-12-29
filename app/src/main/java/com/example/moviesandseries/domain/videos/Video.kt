package com.example.moviesandseries.model.videos


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