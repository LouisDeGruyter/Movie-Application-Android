package com.example.moviesandseries.model.recommendations

import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.domain.RecommendationContainer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a container for recommendations.
 *
 * @property page The current page of recommendations.
 * @property results List of recommendation media items.
 * @property totalPages The total number of pages available.
 * @property totalResults The total number of recommendations.
 */
@JsonClass(generateAdapter = true)
data class RecommendationContainerApi(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<RecommendationMediaApi?>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)

/**
 * Extension function to convert [RecommendationContainerApi] to [RecommendationContainer] domain object.
 */
fun RecommendationContainerApi.asDomainObject(): RecommendationContainer {
    return RecommendationContainer(
        page = page,
        results = results.map { it?.asDomainObject() ?: MediaIndex() },
        totalPages = totalPages,
        totalResults = totalResults,
    )
}
