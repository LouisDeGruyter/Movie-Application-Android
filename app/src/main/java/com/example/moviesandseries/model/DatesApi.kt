package com.example.moviesandseries.model


import com.example.moviesandseries.domain.Dates
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DatesApi(
    @Json(name = "maximum")
    val maximum: String,
    @Json(name = "minimum")
    val minimum: String
)
fun DatesApi.asDomainObject(): Dates {
    return Dates(
        maximum = maximum,
        minimum = minimum,
    )
}
