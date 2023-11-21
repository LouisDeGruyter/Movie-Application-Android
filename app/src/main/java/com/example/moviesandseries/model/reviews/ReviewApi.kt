package com.example.moviesandseries.model.reviews


import com.example.moviesandseries.domain.reviews.Review
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewApi(
    @Json(name = "author")
    val author: String,
    @Json(name = "author_details")
    val authorDetails: AuthorDetailsApi,
    @Json(name = "content")
    val content: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "url")
    val url: String
)
fun ReviewApi.asDomainObject(): Review {
    return Review(
        author = author,
        authorDetails = authorDetails.asDomainObject(),
        content = content,
        createdAt = createdAt,
        id = id,
        updatedAt = updatedAt,
        url = url
    )
}
