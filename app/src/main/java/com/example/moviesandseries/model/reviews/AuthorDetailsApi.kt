package com.example.moviesandseries.model.reviews


import com.example.moviesandseries.domain.reviews.Author
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorDetailsApi(
    @Json(name = "avatar_path")
    val avatarPath: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "rating")
    val rating: Int,
    @Json(name = "username")
    val username: String
)
fun AuthorDetailsApi.asDomainObject(): Author {
    return Author(
        avatarPath = avatarPath,
        name = name,
        rating = rating,
        username = username
    )
}
