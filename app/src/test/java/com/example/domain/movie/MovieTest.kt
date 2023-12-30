package com.example.domain.movie

import com.example.moviesandseries.domain.Collection
import com.example.moviesandseries.domain.Genre
import com.example.moviesandseries.domain.ProductionCompany
import com.example.moviesandseries.domain.ProductionCountry
import com.example.moviesandseries.domain.SpokenLanguage
import com.example.moviesandseries.domain.movie.Movie
import com.example.moviesandseries.domain.movie.asMediaIndexObject
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieTest {

    @Test
    fun `create Movie with default values`() {
        val movie = Movie()

        assertEquals(false, movie.adult)
        assertEquals("", movie.backdropPath)
        assertEquals(Collection(), movie.belongsToCollection)
        assertEquals(0, movie.budget)
        assertEquals(emptyList<Genre>(), movie.genres)
        assertEquals("", movie.homepage)
        assertEquals(0, movie.id)
        assertEquals("", movie.imdbId)
        assertEquals("", movie.originalLanguage)
        assertEquals("", movie.originalTitle)
        assertEquals("", movie.overview)
        assertEquals(0.0, movie.popularity, 0.0)
        assertEquals("", movie.posterPath)
        assertEquals(emptyList<ProductionCompany>(), movie.productionCompanies)
        assertEquals(emptyList<ProductionCountry>(), movie.productionCountries)
        assertEquals("", movie.releaseDate)
        assertEquals(0, movie.revenue)
        assertEquals(0, movie.runtime)
        assertEquals(emptyList<SpokenLanguage>(), movie.spokenLanguages)
        assertEquals("", movie.status)
        assertEquals("", movie.tagline)
        assertEquals("", movie.title)
        assertEquals(false, movie.video)
        assertEquals(0.0, movie.voteAverage, 0.0)
        assertEquals(0, movie.voteCount)
        assertEquals(false, movie.isFavorite)
    }

    @Test
    fun `create Movie with specific values`() {
        val genre1 = Genre(id = 1, name = "Action")
        val genre2 = Genre(id = 2, name = "Drama")

        val productionCompany1 = ProductionCompany(id = 101, logoPath = "/logo1.jpg", name = "Company A", originCountry = "US")
        val productionCompany2 = ProductionCompany(id = 102, logoPath = "/logo2.jpg", name = "Company B", originCountry = "CA")

        val productionCountry1 = ProductionCountry(iso3166_1 = "US", name = "United States")
        val productionCountry2 = ProductionCountry(iso3166_1 = "CA", name = "Canada")

        val spokenLanguage1 = SpokenLanguage(englishName = "English", iso639_1 = "en", name = "English")
        val spokenLanguage2 = SpokenLanguage(englishName = "French", iso639_1 = "fr", name = "Français")

        val movie = Movie(
            adult = true,
            backdropPath = "/backdrop.jpg",
            belongsToCollection = Collection(id = 201, name = "Collection A", posterPath = "/poster.jpg"),
            budget = 1000000,
            genres = listOf(genre1, genre2),
            homepage = "http://example.com",
            id = 42,
            imdbId = "tt123456",
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "Movie overview",
            popularity = 9.5,
            posterPath = "/poster.jpg",
            productionCompanies = listOf(productionCompany1, productionCompany2),
            productionCountries = listOf(productionCountry1, productionCountry2),
            releaseDate = "2023-01-01",
            revenue = 5000000,
            runtime = 120,
            spokenLanguages = listOf(spokenLanguage1, spokenLanguage2),
            status = "Released",
            tagline = "Tagline",
            title = "Movie Title",
            video = true,
            voteAverage = 8.0,
            voteCount = 200,
            isFavorite = true,
        )

        assertEquals(true, movie.adult)
        assertEquals("/backdrop.jpg", movie.backdropPath)
        assertEquals(Collection(id = 201, name = "Collection A", posterPath = "/poster.jpg"), movie.belongsToCollection)
        assertEquals(1000000, movie.budget)
        assertEquals(listOf(genre1, genre2), movie.genres)
        assertEquals("http://example.com", movie.homepage)
        assertEquals(42, movie.id)
        assertEquals("tt123456", movie.imdbId)
        assertEquals("en", movie.originalLanguage)
        assertEquals("Original Title", movie.originalTitle)
        assertEquals("Movie overview", movie.overview)
        assertEquals(9.5, movie.popularity, 0.0)
        assertEquals("/poster.jpg", movie.posterPath)
        assertEquals(listOf(productionCompany1, productionCompany2), movie.productionCompanies)
        assertEquals(listOf(productionCountry1, productionCountry2), movie.productionCountries)
        assertEquals("2023-01-01", movie.releaseDate)
        assertEquals(5000000, movie.revenue)
        assertEquals(120, movie.runtime)
        assertEquals(listOf(spokenLanguage1, spokenLanguage2), movie.spokenLanguages)
        assertEquals("Released", movie.status)
        assertEquals("Tagline", movie.tagline)
        assertEquals("Movie Title", movie.title)
        assertEquals(true, movie.video)
        assertEquals(8.0, movie.voteAverage, 0.0)
        assertEquals(200, movie.voteCount)
        assertEquals(true, movie.isFavorite)
    }

    @Test
    fun `convert Movie to MediaIndex`() {
        val movie = Movie(
            adult = false,
            backdropPath = "/backdrop.jpg",
            genres = listOf(Genre(id = 1, name = "Action"), Genre(id = 2, name = "Drama")),
            id = 42,
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "Movie overview",
            popularity = 9.5,
            posterPath = "/poster.jpg",
            releaseDate = "2023-01-01",
            title = "Movie Title",
            video = true,
            voteAverage = 8.0,
            voteCount = 200,
            isFavorite = true,
        )

        val mediaIndex = movie.asMediaIndexObject()

        assertEquals(false, mediaIndex.adult)
        assertEquals("/backdrop.jpg", mediaIndex.backdropPath)
        assertEquals(listOf(1, 2), mediaIndex.genreIds)
        assertEquals(42, mediaIndex.id)
        assertEquals("en", mediaIndex.originalLanguage)
        assertEquals("Original Title", mediaIndex.originalTitle)
        assertEquals("Movie overview", mediaIndex.overview)
        assertEquals(9.5, mediaIndex.popularity, 0.0)
        assertEquals("/poster.jpg", mediaIndex.posterPath)
        assertEquals("2023-01-01", mediaIndex.releaseDate)
        assertEquals("Movie Title", mediaIndex.title)
        assertEquals(true, mediaIndex.video)
        assertEquals(8.0, mediaIndex.voteAverage, 0.0)
        assertEquals(200, mediaIndex.voteCount)
        assertEquals(null, mediaIndex.originCountry)
        assertEquals("movie", mediaIndex.mediaType)
        assertEquals(true, mediaIndex.isFavorite)
    }
}
