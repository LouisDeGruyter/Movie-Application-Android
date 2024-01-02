package com.example.moviesandseries.domain.reviews

/**
 * Data class representing a container for a collection of movie or series reviews.
 *
 * @property id The unique identifier for the review container.
 * @property page The current page number of the review container.
 * @property results A list containing individual reviews within the container.
 * @property totalPages The total number of pages available for the reviews.
 * @property totalResults The total number of reviews available.
 */
data class ReviewContainer(
    var id: Int = 0,
    var page: Int = 1,
    var results: List<Review> = listOf(),
    var totalPages: Int = 1,
    var totalResults: Int = 0,
)
