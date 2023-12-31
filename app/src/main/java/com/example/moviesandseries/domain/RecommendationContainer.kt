package com.example.moviesandseries.domain

import com.example.moviesandseries.domain.MediaIndex

data class RecommendationContainer(
    var page: Int = 1,
    var results: List<MediaIndex> = listOf(),
    var totalPages: Int = 1,
    var totalResults: Int = 0,
)

