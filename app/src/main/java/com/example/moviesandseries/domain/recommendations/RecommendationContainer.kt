package com.example.moviesandseries.domain.recommendations

data class RecommendationContainer(
    var page: Int = 1,
    var results: List<RecommendationMedia> = listOf(),
    var totalPages: Int = 1,
    var totalResults: Int = 0,
)

