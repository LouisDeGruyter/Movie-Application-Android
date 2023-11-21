package com.example.moviesandseries.model.movie


import com.example.moviesandseries.domain.movie.MovieContainerWithDates
import com.example.moviesandseries.model.DatesApi
import com.example.moviesandseries.model.asDomainObject
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieContainerWithDatesApi(
    @Json(name = "dates")
    val dates: DatesApi,
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<MovieIndexApi>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)
fun MovieContainerWithDatesApi.asDomainObject(): MovieContainerWithDates {
    return MovieContainerWithDates(
        dates = dates.asDomainObject(),
        page = page,
        results = results.map { it?.asDomainObject() },
        totalPages = totalPages,
        totalResults = totalResults,
    )
}
