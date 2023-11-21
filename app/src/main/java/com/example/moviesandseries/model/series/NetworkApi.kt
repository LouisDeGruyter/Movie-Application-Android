package com.example.moviesandseries.model.series


import com.example.moviesandseries.domain.series.Network
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkApi(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "logo_path")
    val logoPath: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "origin_country")
    val originCountry: String?
)
fun NetworkApi.asDomainObject(): Network {
    return Network(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry,
    )
}
