package com.example.moviesandseries.domain.series

import com.example.moviesandseries.domain.MediaIndex

data class SeriesIndex(
    var backdropPath: String?,
    var firstAirDate: String?,
    var genreIds: List<Int>,
    var id: Int,
    var name: String,
    var originCountry: List<String>,
    var originalLanguage: String,
    var originalName: String?,
    var overview: String,
    var popularity: Double,
    var posterPath: String?,
    var voteAverage: Double,
    var voteCount: Int
)
fun SeriesIndex.asMediaIndexObject(): MediaIndex {
    return MediaIndex(
        backdropPath = backdropPath,
        genreIds = genreIds,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = firstAirDate,
        title = name,
        voteAverage = voteAverage,
        voteCount = voteCount,
        adult = null,
        video = null,
        originCountry = originCountry,
    )
}

