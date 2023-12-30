package com.example.moviesandseries.domain.reviews

data class ReviewContainer(
    var id: Int = 0,
    var page: Int = 1,
    var results: List<Review> = listOf(),
    var totalPages: Int = 1,
    var totalResults: Int = 0,
)

