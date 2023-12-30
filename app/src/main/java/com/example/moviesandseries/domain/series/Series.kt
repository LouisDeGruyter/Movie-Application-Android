package com.example.moviesandseries.domain.series

import com.example.moviesandseries.domain.Genre
import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.domain.ProductionCompany
import com.example.moviesandseries.domain.ProductionCountry
import com.example.moviesandseries.domain.SpokenLanguage

data class Series(
    var adult: Boolean = false,
    var backdropPath: String = "",
    var createdBy: List<CreatedBy> = listOf(),
    var episodeRunTime: List<Int> = listOf(),
    var firstAirDate: String = "",
    var genres: List<Genre> = listOf(),
    var homepage: String = "",
    var id: Int = 0,
    var inProduction: Boolean = false,
    var languages: List<String> = listOf(),
    var lastAirDate: String = "",
    var lastEpisodeToAir: Episode = Episode(),
    var name: String,
    var networks: List<Network> = listOf(),
    var nextEpisodeToAir: Episode = Episode(),
    var numberOfEpisodes: Int = 0,
    var numberOfSeasons: Int = 0,
    var originCountry: List<String> = listOf(),
    var originalLanguage: String = "",
    var originalName: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var posterPath: String = "",
    var productionCompanies: List<ProductionCompany> = listOf(),
    var productionCountries: List<ProductionCountry> = listOf(),
    var seasons: List<Season> = listOf(),
    var spokenLanguages: List<SpokenLanguage> = listOf(),
    var status: String = "",
    var tagline: String = "",
    var type: String = "",
    var voteAverage: Double = 0.0,
    var voteCount: Int = 0,
    var isFavorite: Boolean = false,
)
fun Series.asMediaIndexObject(): MediaIndex {
    return MediaIndex(
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genres.map { it.id },
        id = id,
        mediaType = "tv",
        originalLanguage = originalLanguage,
        originalTitle = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = firstAirDate,
        title = name,
        voteAverage = voteAverage,
        voteCount = voteCount,
        originCountry = originCountry,
        isFavorite = isFavorite,

    )
}
