package com.example.moviesandseries.model.credits

import com.example.moviesandseries.domain.credits.Credit
import com.example.moviesandseries.domain.credits.CreditsContainer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a container for movie or series credits.
 *
 * @property cast List of cast credits associated with the media.
 * @property crew List of crew credits associated with the media.
 * @property id The unique identifier of the media for which credits are provided.
 */
@JsonClass(generateAdapter = true)
data class CreditsContainerApi(
    @Json(name = "cast")
    val cast: List<CreditApi?>,
    @Json(name = "crew")
    val crew: List<CreditApi?>,
    @Json(name = "id")
    val id: Int,
)

/**
 * Extension function to convert [CreditsContainerApi] to [CreditsContainer] domain object.
 */
fun CreditsContainerApi.asDomainObject(): CreditsContainer {
    return CreditsContainer(
        cast = cast.map { it?.asDomainObject() ?: Credit() },
        crew = crew.map { it?.asDomainObject() ?: Credit() },
        id = id,
    )
}
