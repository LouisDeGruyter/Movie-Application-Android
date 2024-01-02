package com.example.moviesandseries.domain

/**
 * Data class representing a container for recommendations, including pagination details.
 *
 * @property page The current page number of recommendations.
 * @property results The list of recommended media items, each represented by a [MediaIndex] object.
 * @property totalPages The total number of pages available for recommendations.
 * @property totalResults The total number of recommended media items.
 */
data class RecommendationContainer(
    var page: Int = 1,
    var results: List<MediaIndex> = listOf(),
    var totalPages: Int = 1,
    var totalResults: Int = 0,
)
