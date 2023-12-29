package com.example.moviesandseries.domain.movie

import com.example.moviesandseries.domain.Collection.CollectionDetail
import com.example.moviesandseries.domain.Genre
import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.domain.ProductionCompany
import com.example.moviesandseries.domain.ProductionCountry
import com.example.moviesandseries.domain.SpokenLanguage

data class MovieDetail(
    var adult: Boolean = false,
    var backdropPath: String = "",
    var belongsToCollection: CollectionDetail = CollectionDetail(),
    var budget: Int = 0,
    var genres: List<Genre> = listOf(),
    var homepage: String = "",
    var id: Int = 0,
    var imdbId: String = "",
    var originalLanguage: String = "",
    var originalTitle: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var posterPath: String = "",
    var productionCompanies: List<ProductionCompany> = listOf(),
    var productionCountries: List<ProductionCountry> = listOf(),
    var releaseDate: String = "",
    var revenue: Int = 0,
    var runtime: Int = 0,
    var spokenLanguages: List<SpokenLanguage> = listOf(),
    var status: String = "",
    var tagline: String = "",
    var title: String = "",
    var video: Boolean = false,
    var voteAverage: Double = 0.0,
    var voteCount: Int = 0,
)
fun MovieDetail.asMediaIndexObject(): MediaIndex {
    return MediaIndex(
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genres.map { it.id ?: 0 },
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
        mediaType = "movie",
    )
}
