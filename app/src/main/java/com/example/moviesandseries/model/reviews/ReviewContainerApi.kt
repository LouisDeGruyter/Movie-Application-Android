package com.example.moviesandseries.model.reviews


import com.example.moviesandseries.domain.reviews.ReviewContainer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewContainerApi(
    @Json(name = "id")
    val id: Int,
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<ReviewApi>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)
fun ReviewContainerApi.asDomainObject(): ReviewContainer {
    return ReviewContainer(
        id = id,
        page = page,
        results = results.map { it.asDomainObject() },
        totalPages = totalPages,
        totalResults = totalResults,
    )
}
