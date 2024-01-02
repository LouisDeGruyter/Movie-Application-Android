package com.example.moviesandseries.model.series

import com.example.moviesandseries.domain.series.SeriesContainer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a container for TV series.
 *
 * @property page The current page of series.
 * @property results List of series items.
 * @property totalPages The total number of pages available.
 * @property totalResults The total number of series.
 */
@JsonClass(generateAdapter = true)
data class SeriesContainerApi(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<SeriesIndexApi>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)

/**
 * Extension function to convert [SeriesContainerApi] to [SeriesContainer] domain object.
 */
fun SeriesContainerApi.asDomainObject(): SeriesContainer {
    return SeriesContainer(
        page = page,
        results = results.map { it.asDomainObject() },
        totalPages = totalPages,
        totalResults = totalResults,
    )
}
