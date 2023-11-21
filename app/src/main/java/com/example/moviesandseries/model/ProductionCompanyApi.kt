package com.example.moviesandseries.model


import com.example.moviesandseries.domain.ProductionCompany
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompanyApi(
    @Json(name = "id")
    val id: Int,
    @Json(name = "logo_path")
    val logoPath: String?,
    @Json(name = "name")
    val name: String,
    @Json(name = "origin_country")
    val originCountry: String?
)
fun ProductionCompanyApi.asDomainObject(): ProductionCompany {
    return ProductionCompany(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry,
    )
}
