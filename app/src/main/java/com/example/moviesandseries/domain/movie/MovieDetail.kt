package com.example.moviesandseries.domain.movie

import com.example.moviesandseries.domain.Collection.CollectionIndex
import com.example.moviesandseries.domain.Genre
import com.example.moviesandseries.domain.ProductionCompany
import com.example.moviesandseries.domain.ProductionCountry
import com.example.moviesandseries.domain.SpokenLanguage

data class MovieDetail(
    var adult: Boolean = false,
    var backdropPath: String = "",
    var belongsToCollection: CollectionIndex = CollectionIndex(),
    var budget: Int = 0,
    var genres: List<Genre?> = listOf(),
    var homepage: String = "",
    var id: Int = 0,
    var imdbId: String = "",
    var originalLanguage: String = "",
    var originalTitle: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var posterPath: String = "",
    var productionCompanies: List<ProductionCompany?> = listOf(),
    var productionCountries: List<ProductionCountry?> = listOf(),
    var releaseDate: String = "",
    var revenue: Int = 0,
    var runtime: Int = 0,
    var spokenLanguages: List<SpokenLanguage?> = listOf(),
    var status: String = "",
    var tagline: String = "",
    var title: String = "",
    var video: Boolean = false,
    var voteAverage: Double = 0.0,
    var voteCount: Int = 0,
)
