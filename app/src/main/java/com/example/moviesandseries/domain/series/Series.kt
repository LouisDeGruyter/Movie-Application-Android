package com.example.moviesandseries.domain.series

import com.example.moviesandseries.domain.Genre
import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.domain.ProductionCompany
import com.example.moviesandseries.domain.ProductionCountry
import com.example.moviesandseries.domain.SpokenLanguage

/**
 * Data class representing a TV series.
 *
 * @property adult Indicates if the series is intended for adult audiences.
 * @property backdropPath The file path to the backdrop image of the series.
 * @property createdBy The list of individuals who created the series.
 * @property episodeRunTime The list of runtimes for each episode of the series.
 * @property firstAirDate The date when the series first aired.
 * @property genres The list of genres associated with the series.
 * @property homepage The homepage URL of the series.
 * @property id The unique identifier for the series.
 * @property inProduction Indicates if the series is currently in production.
 * @property languages The list of languages associated with the series.
 * @property lastAirDate The date when the series last aired.
 * @property lastEpisodeToAir The details of the last episode that aired.
 * @property name The name of the series.
 * @property networks The list of networks associated with the series.
 * @property nextEpisodeToAir The details of the next episode to air.
 * @property numberOfEpisodes The total number of episodes in the series.
 * @property numberOfSeasons The total number of seasons in the series.
 * @property originCountry The list of countries where the series originated.
 * @property originalLanguage The original language of the series.
 * @property originalName The original name of the series.
 * @property overview A brief overview or summary of the series.
 * @property popularity The popularity score of the series.
 * @property posterPath The file path to the poster image of the series.
 * @property productionCompanies The list of production companies associated with the series.
 * @property productionCountries The list of production countries associated with the series.
 * @property seasons The list of seasons in the series.
 * @property spokenLanguages The list of spoken languages in the series.
 * @property status The status of the series (e.g., "Returning Series," "Ended," etc.).
 * @property tagline The tagline of the series.
 * @property type The type of the series.
 * @property voteAverage The average vote rating for the series.
 * @property voteCount The total number of votes for the series.
 * @property isFavorite Indicates if the series is marked as a favorite.
 */
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
    var name: String = "",
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

/**
 * Extension function to convert a [Series] object to a [MediaIndex] object.
 *
 * @return A [MediaIndex] object representing the series.
 */
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
