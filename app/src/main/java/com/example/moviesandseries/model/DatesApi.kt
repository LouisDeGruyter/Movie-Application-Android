package com.example.moviesandseries.model

import com.example.moviesandseries.domain.Dates
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of date information.
 *
 * @property maximum The maximum date value.
 * @property minimum The minimum date value.
 */
@JsonClass(generateAdapter = true)
data class DatesApi(
    @Json(name = "maximum")
    val maximum: String,
    @Json(name = "minimum")
    val minimum: String
)

/**
 * Extension function to convert [DatesApi] to [Dates] domain object.
 */
fun DatesApi.asDomainObject(): Dates {
    return Dates(
        maximum = maximum,
        minimum = minimum,
    )
}
