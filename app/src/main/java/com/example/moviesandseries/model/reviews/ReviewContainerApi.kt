package com.example.moviesandseries.model.reviews

import com.example.moviesandseries.domain.reviews.ReviewContainer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a container for reviews.
 *
 * @property id The unique identifier of the review container.
 * @property page The current page of reviews.
 * @property results List of review items.
 * @property totalPages The total number of pages available.
 * @property totalResults The total number of reviews.
 */
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

/**
 * Extension function to convert [ReviewContainerApi] to [ReviewContainer] domain object.
 */
fun ReviewContainerApi.asDomainObject(): ReviewContainer {
    return ReviewContainer(
        id = id,
        page = page,
        results = results.map { it.asDomainObject() },
        totalPages = totalPages,
        totalResults = totalResults,
    )
}
