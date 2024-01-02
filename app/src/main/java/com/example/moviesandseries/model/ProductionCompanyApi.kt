package com.example.moviesandseries.model

import com.example.moviesandseries.domain.ProductionCompany
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a production company associated with a movie or series.
 *
 * @property id The unique identifier of the production company.
 * @property logoPath The path to the logo of the production company.
 * @property name The name of the production company.
 * @property originCountry The country of origin of the production company.
 */
@JsonClass(generateAdapter = true)
data class ProductionCompanyApi(
    @Json(name = "id")
    val id: Int,
    @Json(name = "logo_path")
    val logoPath: String?,
    @Json(name = "name")
    val name: String,
    @Json(name = "origin_country")
    val originCountry: String?,
)

/**
 * Extension function to convert [ProductionCompanyApi] to [ProductionCompany] domain object.
 */
fun ProductionCompanyApi.asDomainObject(): ProductionCompany {
    return ProductionCompany(
        id = id,
        logoPath = logoPath ?: "",
        name = name,
        originCountry = originCountry ?: "",
    )
}
