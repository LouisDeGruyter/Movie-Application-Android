package com.example.moviesandseries.domain.recommendations

import com.example.moviesandseries.domain.MediaIndex

data class RecommendationContainer(
    var page: Int,
    var results: List<RecommendationMedia>,
    var totalPages: Int,
    var totalResults: Int
)

