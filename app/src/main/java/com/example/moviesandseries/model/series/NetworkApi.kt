package com.example.moviesandseries.model.series

import com.example.moviesandseries.domain.series.Network
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a network associated with a TV series.
 *
 * @property id The unique identifier of the network.
 * @property logoPath The path to the logo image of the network.
 * @property name The name of the network.
 * @property originCountry The origin country of the network.
 */
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

/**
 * Extension function to convert [NetworkApi] to [Network] domain object.
 */
fun NetworkApi.asDomainObject(): Network {
    return Network(
        id = id ?: 0,
        logoPath = logoPath ?: "",
        name = name ?: "",
        originCountry = originCountry ?: "",
    )
}
