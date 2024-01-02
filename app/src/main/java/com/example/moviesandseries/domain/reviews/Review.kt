package com.example.moviesandseries.domain.reviews

/**
 * Data class representing a movie or series review.
 *
 * @property author The name of the author of the review.
 * @property authorDetails Detailed information about the author.
 * @property content The content or text of the review.
 * @property createdAt The timestamp when the review was created.
 * @property id The unique identifier for the review.
 * @property updatedAt The timestamp when the review was last updated.
 * @property url The URL link to the full review.
 */
data class Review(
    var author: String = "",
    var authorDetails: Author = Author(),
    var content: String = "",
    var createdAt: String = "",
    var id: String = "",
    var updatedAt: String = "",
    var url: String = "",
)
