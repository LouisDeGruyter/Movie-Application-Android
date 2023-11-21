package com.example.moviesandseries.domain.recommendations

data class RecommendationContainer(
    var page: Int,
    var results: List<RecommendationMedia>,
    var totalPages: Int,
    var totalResults: Int
)

