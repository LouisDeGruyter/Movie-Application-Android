package com.example.moviesandseries.domain.recommendations

import com.example.moviesandseries.domain.MediaIndex

data class RecommendationMedia(
    var adult: Boolean,
    var backdropPath: String,
    var genreIds: List<Int>,
    var id: Int,
    var mediaType: String,
    var originalLanguage: String,
    var originalTitle: String,
    var overview: String,
    var popularity: Double,
    var posterPath: String,
    var releaseDate: String,
    var title: String,
    var video: Boolean,
    var voteAverage: Double,
    var voteCount: Int
)
fun RecommendationMedia.asMediaIndexObject(): MediaIndex {
    return MediaIndex(
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        id = id,
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
        mediaType = mediaType,
    )
}

