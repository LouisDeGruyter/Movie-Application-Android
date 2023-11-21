package com.example.moviesandseries.model.credits

import com.example.moviesandseries.domain.credits.Cast
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CastApi(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "cast_id")
    val castId: Int,
    @Json(name = "character")
    val character: String,
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
    val order: Int,
    @Json(name = "original_name")
    val originalName: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "profile_path")
    val profilePath: String,
)
fun CastApi.asDomainObject(): Cast {
    return Cast(
        adult = adult,
        castId = castId,
        character = character,
        creditId = creditId,
        gender = gender,
        id = id,
        knownForDepartment = knownForDepartment,
        name = name,
        order = order,
        originalName = originalName,
        popularity = popularity,
        profilePath = profilePath,
    )
}
