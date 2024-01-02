package com.example.moviesandseries.model.series

import com.example.moviesandseries.domain.series.CreatedBy
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a creator or contributor of a TV series.
 *
 * @property creditId The unique identifier of the credit for the creator.
 * @property gender The gender of the creator.
 * @property id The unique identifier of the creator.
 * @property name The name of the creator.
 * @property profilePath The path to the profile image of the creator.
 */
@JsonClass(generateAdapter = true)
data class CreatedByApi(
    @Json(name = "credit_id")
    val creditId: String?,
    @Json(name = "gender")
    val gender: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "profile_path")
    val profilePath: String?,
)

/**
 * Extension function to convert [CreatedByApi] to [CreatedBy] domain object.
 */
fun CreatedByApi.asDomainObject(): CreatedBy {
    return CreatedBy(
        creditId = creditId ?: "",
        gender = gender ?: 0,
        id = id ?: 0,
        name = name ?: "",
        profilePath = profilePath ?: "",
    )
}
