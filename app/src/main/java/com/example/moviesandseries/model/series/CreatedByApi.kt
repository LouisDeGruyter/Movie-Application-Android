package com.example.moviesandseries.model.series

import com.example.moviesandseries.domain.series.CreatedBy
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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
fun CreatedByApi.asDomainObject(): CreatedBy {
    return CreatedBy(
        creditId = creditId ?: "",
        gender = gender ?: 0,
        id = id ?: 0,
        name = name ?: "",
        profilePath = profilePath ?: "",
    )
}
