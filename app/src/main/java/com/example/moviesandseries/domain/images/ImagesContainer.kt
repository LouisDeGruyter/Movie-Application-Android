package com.example.moviesandseries.domain.images

data class ImagesContainer(
    var backdrops: List<Image>,
    var id: Int,
    var logos: List<Image>,
    var posters: List<Image>
)

