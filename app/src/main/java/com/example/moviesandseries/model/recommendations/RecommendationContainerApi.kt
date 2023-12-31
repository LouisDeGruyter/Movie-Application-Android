package com.example.moviesandseries.model.recommendations


import com.example.moviesandseries.domain.RecommendationContainer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecommendationContainerApi(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<RecommendationMediaApi>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)
fun RecommendationContainerApi.asDomainObject(): RecommendationContainer {
    return RecommendationContainer(
        page = page,
        results = results.map { it.asDomainObject() },
        totalPages = totalPages,
        totalResults = totalResults,
    )
}
