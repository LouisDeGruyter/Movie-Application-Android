package com.example.moviesandseries.model.credits

import com.example.moviesandseries.domain.credits.CreditsContainer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditsContainerApi(
    @Json(name = "cast")
    val cast: List<CastApi?>,
    @Json(name = "crew")
    val crew: List<CrewApi?>,
    @Json(name = "id")
    val id: Int,
)

fun CreditsContainerApi.asDomainObject(): CreditsContainer {
    return CreditsContainer(
        cast = cast.map { it?.asDomainObject() },
        crew = crew.map { it?.asDomainObject() },
        id = id,
    )
}
