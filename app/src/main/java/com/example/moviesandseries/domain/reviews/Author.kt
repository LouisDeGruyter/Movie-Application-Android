package com.example.moviesandseries.domain.reviews

/**
 * Data class representing the author information for a review.
 *
 * @property avatarPath The path to the avatar image of the author.
 * @property name The name of the author.
 * @property rating The rating given by the author.
 * @property username The username of the author.
 */
data class Author(
    var avatarPath: String = "",
    var name: String = "",
    var rating: Int = 0,
    var username: String = "",
)
