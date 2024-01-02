package com.example.moviesandseries.model.reviews

import com.example.moviesandseries.domain.reviews.Author
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of author details in reviews.
 *
 * @property avatarPath The path to the author's avatar.
 * @property name The name of the author.
 * @property rating The rating given by the author.
 * @property username The username of the author.
 */
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

/**
 * Extension function to convert [AuthorDetailsApi] to [Author] domain object.
 */
fun AuthorDetailsApi.asDomainObject(): Author {
    return Author(
        avatarPath = avatarPath,
        name = name,
        rating = rating,
        username = username
    )
}
