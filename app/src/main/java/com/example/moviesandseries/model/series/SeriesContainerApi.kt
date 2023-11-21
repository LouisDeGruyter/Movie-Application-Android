package com.example.moviesandseries.model.series


import com.example.moviesandseries.domain.series.SeriesContainer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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
fun SeriesContainerApi.asDomainObject(): SeriesContainer {
    return SeriesContainer(
        page = page,
        results = results.map { it?.asDomainObject() },
        totalPages = totalPages,
        totalResults = totalResults,
    )
}
