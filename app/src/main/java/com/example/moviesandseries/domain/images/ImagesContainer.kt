package com.example.moviesandseries.domain.images

data class ImagesContainer(
    var backdrops: List<Image> = listOf(),
    var id: Int = 0,
    var logos: List<Image> = listOf(),
    var posters: List<Image> = listOf(),
)

