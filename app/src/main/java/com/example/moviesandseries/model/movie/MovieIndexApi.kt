package com.example.moviesandseries.model.movie

import com.example.moviesandseries.domain.movie.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API representation of basic movie information used in movie lists.
 *
 * @property adult Indicates if the movie is intended for an adult audience.
 * @property backdropPath The path to the backdrop image of the movie.
 * @property genreIds List of genre IDs associated with the movie.
 * @property id The unique identifier of the movie.
 * @property originalLanguage The original language of the movie.
 * @property originalTitle The original title of the movie.
 * @property overview A brief overview or synopsis of the movie.
 * @property popularity The popularity score of the movie.
 * @property posterPath The path to the poster image of the movie.
 * @property releaseDate The release date of the movie.
 * @property title The title of the movie.
 * @property video Indicates if the movie has a video.
 * @property voteAverage The average vote score of the movie.
 * @property voteCount The total number of votes received by the movie.
 */
@JsonClass(generateAdapter = true)
data class MovieIndexApi(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "release_date")
    val releaseDate: String?,
    @Json(name = "title")
    val title: String,
    @Json(name = "video")
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int,
)

/**
 * Extension function to convert [MovieIndexApi] to [Movie] domain object.
 */
fun MovieIndexApi.asDomainObject(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath ?: "",
        releaseDate = releaseDate ?: "",
        voteAverage = voteAverage,
        voteCount = voteCount,
        overview = overview ?: "",
        adult = adult,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle ?: "",
        popularity = popularity,
        video = video,
    )
}
