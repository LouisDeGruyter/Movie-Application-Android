package com.example.moviesandseries.model.movie

import com.example.moviesandseries.domain.Collection.CollectionDetail
import com.example.moviesandseries.domain.ProductionCompany
import com.example.moviesandseries.domain.ProductionCountry
import com.example.moviesandseries.domain.movie.MovieDetail
import com.example.moviesandseries.model.GenreApi
import com.example.moviesandseries.model.ProductionCompanyApi
import com.example.moviesandseries.model.ProductionCountryApi
import com.example.moviesandseries.model.SpokenLanguageApi
import com.example.moviesandseries.model.asDomainObject
import com.example.moviesandseries.model.collection.CollectionIndexApi
import com.example.moviesandseries.model.collection.asDomainObject
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailApi(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "belongs_to_collection")
    val belongsToCollection: CollectionIndexApi?,
    @Json(name = "budget")
    val budget: Int,
    @Json(name = "genres")
    val genres: List<GenreApi>,
    @Json(name = "homepage")
    val homepage: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "imdb_id")
    val imdbId: String,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompanyApi?>,
    @Json(name = "production_countries")
    val productionCountries: List<ProductionCountryApi?>,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "revenue")
    val revenue: Int?,
    @Json(name = "runtime")
    val runtime: Int,
    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguageApi>,
    @Json(name = "status")
    val status: String,
    @Json(name = "tagline")
    val tagline: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "video")
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int,
)
fun MovieDetailApi.asDomainObject(): MovieDetail {
    return MovieDetail(
        adult = adult,
        backdropPath = backdropPath,
        belongsToCollection = belongsToCollection?.asDomainObject() ?: CollectionDetail(),
        budget = budget,
        genres = genres.map { it.asDomainObject() },
        homepage = homepage ?: "",
        id = id,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        productionCompanies = productionCompanies.map { it?.asDomainObject() ?: ProductionCompany() },
        productionCountries = productionCountries.map { it?.asDomainObject() ?: ProductionCountry() },
        releaseDate = releaseDate,
        revenue = revenue ?: 0,
        runtime = runtime,
        spokenLanguages = spokenLanguages.map { it.asDomainObject() },
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
}
