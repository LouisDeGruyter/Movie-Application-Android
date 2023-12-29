package com.example.moviesandseries.domain.movie

import com.example.moviesandseries.domain.Collection.CollectionIndex
import com.example.moviesandseries.domain.Genre
import com.example.moviesandseries.domain.ProductionCompany
import com.example.moviesandseries.domain.ProductionCountry
import com.example.moviesandseries.domain.SpokenLanguage

data class MovieDetail(
    var adult: Boolean,
    var backdropPath: String,
    var belongsToCollection: CollectionIndex?,
    var budget: Int,
    var genres: List<Genre?>,
    var homepage: String?,
    var id: Int,
    var imdbId: String,
    var originalLanguage: String,
    var originalTitle: String,
    var overview: String,
    var popularity: Double,
    var posterPath: String,
    var productionCompanies: List<ProductionCompany?>,
    var productionCountries: List<ProductionCountry?>,
    var releaseDate: String,
    var revenue: Int?,
    var runtime: Int,
    var spokenLanguages: List<SpokenLanguage?>,
    var status: String,
    var tagline: String,
    var title: String,
    var video: Boolean,
    var voteAverage: Double,
    var voteCount: Int,
)
