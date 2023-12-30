package com.example.moviesandseries.model.credits

import com.example.moviesandseries.domain.credits.Credit
import com.example.moviesandseries.domain.credits.CreditsContainer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditsContainerApi(
    @Json(name = "cast")
    val cast: List<CreditApi?>,
    @Json(name = "crew")
    val crew: List<CreditApi?>,
    @Json(name = "id")
    val id: Int,
)

fun CreditsContainerApi.asDomainObject(): CreditsContainer {
    return CreditsContainer(
        cast = cast.map { it?.asDomainObject() ?: Credit() },
        crew = crew.map { it?.asDomainObject() ?: Credit() },
        id = id,
    )
}
