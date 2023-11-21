package com.example.moviesandseries.domain.images

data class ImagesContainer(
    var backdrops: List<Backdrop>,
    var id: Int,
    var logos: List<Logo>,
    var posters: List<Poster>
)

