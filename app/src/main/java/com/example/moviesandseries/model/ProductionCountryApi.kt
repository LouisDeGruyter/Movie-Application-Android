package com.example.moviesandseries.model

import com.example.moviesandseries.domain.ProductionCountry
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a production country associated with a movie or series.
 *
 * @property iso31661 The ISO 3166-1 code of the production country.
 * @property name The name of the production country.
 */
@JsonClass(generateAdapter = true)
data class ProductionCountryApi(
    @Json(name = "iso_3166_1")
    val iso31661: String,
    @Json(name = "name")
    val name: String
)

/**
 * Extension function to convert [ProductionCountryApi] to [ProductionCountry] domain object.
 */
fun ProductionCountryApi.asDomainObject(): ProductionCountry {
    return ProductionCountry(
        iso3166_1 = iso31661,
        name = name,
    )
}
