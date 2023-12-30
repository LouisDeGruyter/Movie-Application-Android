package com.example.moviesandseries.domain.recommendations

import com.example.moviesandseries.domain.MediaIndex

data class RecommendationMedia(
    var adult: Boolean = false,
    var backdropPath: String = "",
    var genreIds: List<Int> = listOf(),
    var id: Int = 0,
    var mediaType: String = "",
    var originalLanguage: String = "",
    var originalTitle: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var posterPath: String = "",
    var releaseDate: String = "",
    var title: String = "",
    var video: Boolean = false,
    var voteAverage: Double = 0.0,
    var voteCount: Int = 0,
)
fun RecommendationMedia.asMediaIndexObject(): MediaIndex {
    return MediaIndex(
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        id = id,
        mediaType = mediaType,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        originCountry = null,
    )
}

