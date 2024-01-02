package com.example.moviesandseries.domain

import com.example.moviesandseries.domain.movie.Movie

/**
 * Data class representing a collection of movies.
 *
 * @property backdropPath The path to the backdrop image of the collection.
 * @property id The unique identifier of the collection.
 * @property name The name of the collection.
 * @property overview An overview or description of the collection.
 * @property parts A list of [Movie] objects representing the movies that belong to the collection.
 * @property posterPath The path to the poster image of the collection.
 */
data class Collection(
    var backdropPath: String = "",
    var id: Int = 0,
    var name: String = "",
    var overview: String = "",
    var parts: List<Movie> = listOf(),
    var posterPath: String = ""
)
