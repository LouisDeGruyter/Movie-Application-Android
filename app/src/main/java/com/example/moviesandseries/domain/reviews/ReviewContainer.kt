package com.example.moviesandseries.domain.reviews

data class ReviewContainer(
    var id: Int,
    var page: Int,
    var results: List<Review>,
    var totalPages: Int,
    var totalResults: Int
)

