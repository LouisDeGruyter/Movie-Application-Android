package com.example.moviesandseries.model.credits

import com.example.moviesandseries.domain.credits.Credit
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a credit for a movie or series.
 *
 * @property adult Indicates if the credit is associated with an adult content.
 * @property castId The cast ID associated with the credit. Can be null.
 * @property character The character name associated with the credit. Can be null.
 * @property creditId The unique identifier for the credit.
 * @property gender The gender of the person associated with the credit.
 * @property id The unique identifier of the person associated with the credit.
 * @property knownForDepartment The department for which the person is known.
 * @property name The name of the person associated with the credit.
 * @property order The order of the credit in the cast or crew. Can be null.
 * @property originalName The original name of the person associated with the credit.
 * @property popularity The popularity score of the person associated with the credit.
 * @property profilePath The path to the profile image of the person. Can be null.
 */
@JsonClass(generateAdapter = true)
data class CreditApi(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "cast_id")
    val castId: Int?,
    @Json(name = "character")
    val character: String?,
    @Json(name = "credit_id")
    val creditId: String,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "known_for_department")
    val knownForDepartment: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "order")
    val order: Int?,
    @Json(name = "original_name")
    val originalName: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "profile_path")
    val profilePath: String?,
)

/**
 * Extension function to convert [CreditApi] to [Credit] domain object.
 */
fun CreditApi.asDomainObject(): Credit {
    return Credit(
        adult = adult,
        castId = castId ?: 0,
        character = character ?: "",
        creditId = creditId,
        gender = gender,
        id = id,
        knownForDepartment = knownForDepartment,
        name = name,
        order = order ?: 0,
        originalName = originalName,
        popularity = popularity,
        profilePath = profilePath ?: "",
    )
}
