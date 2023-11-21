package com.example.moviesandseries.model


import com.example.moviesandseries.domain.ProductionCountry
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCountryApi(
    @Json(name = "iso_3166_1")
    val iso31661: String,
    @Json(name = "name")
    val name: String
)
fun ProductionCountryApi.asDomainObject(): ProductionCountry {
    return ProductionCountry(
        iso3166_1 = iso31661,
        name = name,
    )
}
