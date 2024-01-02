package com.example.moviesandseries.model.reviews

import com.example.moviesandseries.domain.reviews.Review
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of a review.
 *
 * @property author The name of the author of the review.
 * @property authorDetails Details of the author, including avatar and rating.
 * @property content The content or text of the review.
 * @property createdAt The timestamp when the review was created.
 * @property id The unique identifier of the review.
 * @property updatedAt The timestamp when the review was last updated.
 * @property url The URL associated with the review.
 */
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

/**
 * Extension function to convert [ReviewApi] to [Review] domain object.
 */
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
