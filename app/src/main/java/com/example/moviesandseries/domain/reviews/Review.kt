package com.example.moviesandseries.domain.reviews

data class Review(
    var author: String = "",
    var authorDetails: Author = Author(),
    var content: String = "",
    var createdAt: String = "",
    var id: String = "",
    var updatedAt: String = "",
    var url: String = "",
)

