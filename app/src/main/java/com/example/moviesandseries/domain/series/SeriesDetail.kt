package com.example.moviesandseries.domain.series

import com.example.moviesandseries.domain.Genre
import com.example.moviesandseries.domain.ProductionCompany
import com.example.moviesandseries.domain.ProductionCountry
import com.example.moviesandseries.domain.SpokenLanguage
import com.example.moviesandseries.domain.series.episode.Episode
import com.example.moviesandseries.domain.series.season.Season

data class SeriesDetail(
    var adult: Boolean?,
    var backdropPath: String?,
    var createdBy: List<CreatedBy?>,
    var episodeRunTime: List<Int>,
    var firstAirDate: String?,
    var genres: List<Genre?>,
    var homepage: String?,
    var id: Int,
    var inProduction: Boolean,
    var languages: List<String?>,
    var lastAirDate: String?,
    var lastEpisodeToAir: Episode?,
    var name: String,
    var networks: List<Network?>,
    var nextEpisodeToAir: Episode?,
    var numberOfEpisodes: Int,
    var numberOfSeasons: Int,
    var originCountry: List<String?>,
    var originalLanguage: String?,
    var originalName: String?,
    var overview: String,
    var popularity: Double,
    var posterPath: String,
    var productionCompanies: List<ProductionCompany>,
    var productionCountries: List<ProductionCountry>,
    var seasons: List<Season>,
    var spokenLanguages: List<SpokenLanguage>,
    var status: String,
    var tagline: String,
    var type: String,
    var voteAverage: Double,
    var voteCount: Int
)

