package com.example.moviesandseries.model.movie

import com.example.moviesandseries.domain.movie.MovieContainer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a container for movie data.
 *
 * @property page The current page of the movie container.
 * @property results List of movie index API representations.
 * @property totalPages The total number of pages in the movie container.
 * @property totalResults The total number of results in the movie container.
 */
@JsonClass(generateAdapter = true)
data class MovieContainerApi(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<MovieIndexApi>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int,
)

/**
 * Extension function to convert [MovieContainerApi] to [MovieContainer] domain object.
 */
fun MovieContainerApi.asDomainObject(): MovieContainer {
    return MovieContainer(
        page = page,
        results = results.map { it.asDomainObject() },
        totalPages = totalPages,
        totalResults = totalResults,
    )
}
