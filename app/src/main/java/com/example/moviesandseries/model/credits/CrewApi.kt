package com.example.moviesandseries.model.credits


import com.example.moviesandseries.domain.credits.Crew
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CrewApi(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "credit_id")
    val creditId: String,
    @Json(name = "department")
    val department: String,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "job")
    val job: String,
    @Json(name = "known_for_department")
    val knownForDepartment: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "original_name")
    val originalName: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "profile_path")
    val profilePath: String
)

fun CrewApi.asDomainObject(): Crew {
    return Crew(
        adult = adult,
        creditId = creditId,
        department = department,
        gender = gender,
        id = id,
        job = job,
        knownForDepartment = knownForDepartment,
        name = name,
        originalName = originalName,
        popularity = popularity,
        profilePath = profilePath,
    )
}
