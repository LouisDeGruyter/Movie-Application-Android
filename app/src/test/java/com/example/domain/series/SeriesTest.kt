package com.example.domain.series

import com.example.moviesandseries.domain.Genre
import com.example.moviesandseries.domain.ProductionCompany
import com.example.moviesandseries.domain.ProductionCountry
import com.example.moviesandseries.domain.SpokenLanguage
import com.example.moviesandseries.domain.series.CreatedBy
import com.example.moviesandseries.domain.series.Episode
import com.example.moviesandseries.domain.series.Network
import com.example.moviesandseries.domain.series.Season
import com.example.moviesandseries.domain.series.Series
import com.example.moviesandseries.domain.series.asMediaIndexObject
import org.junit.Assert.assertEquals
import org.junit.Test

class SeriesTest {

    @Test
    fun `create Series with default values`() {
        val series = Series(name = "Test Series")
        assertEquals(false, series.adult)
        assertEquals("", series.backdropPath)
        assertEquals(emptyList<CreatedBy>(), series.createdBy)
        assertEquals(emptyList<Int>(), series.episodeRunTime)
        assertEquals("", series.firstAirDate)
        assertEquals(emptyList<Genre>(), series.genres)
        assertEquals("", series.homepage)
        assertEquals(0, series.id)
        assertEquals(false, series.inProduction)
        assertEquals(emptyList<String>(), series.languages)
        assertEquals("", series.lastAirDate)
        assertEquals(Episode(), series.lastEpisodeToAir)
        assertEquals("Test Series", series.name)
        assertEquals(emptyList<Network>(), series.networks)
        assertEquals(Episode(), series.nextEpisodeToAir)
        assertEquals(0, series.numberOfEpisodes)
        assertEquals(0, series.numberOfSeasons)
        assertEquals(emptyList<String>(), series.originCountry)
        assertEquals("", series.originalLanguage)
        assertEquals("", series.originalName)
        assertEquals("", series.overview)
        assertEquals(0.0, series.popularity, 0.01)
        assertEquals("", series.posterPath)
        assertEquals(emptyList<ProductionCompany>(), series.productionCompanies)
        assertEquals(emptyList<ProductionCountry>(), series.productionCountries)
        assertEquals(emptyList<Season>(), series.seasons)
        assertEquals(emptyList<SpokenLanguage>(), series.spokenLanguages)
        assertEquals("", series.status)
        assertEquals("", series.tagline)
        assertEquals("", series.type)
        assertEquals(0.0, series.voteAverage, 0.01)
        assertEquals(0, series.voteCount)
        assertEquals(false, series.isFavorite)
    }

    @Test
    fun `create Series with specific values`() {
        val series = Series(
            adult = true,
            backdropPath = "/backdrop.jpg",
            createdBy = listOf(CreatedBy(name = "Creator")),
            episodeRunTime = listOf(30, 45),
            firstAirDate = "2023-01-01",
            genres = listOf(Genre(id = 1, name = "Drama")),
            homepage = "http://example.com",
            id = 123,
            inProduction = true,
            languages = listOf("English", "Spanish"),
            lastAirDate = "2023-12-31",
            lastEpisodeToAir = Episode(name = "Last Episode"),
            name = "Test Series",
            networks = listOf(Network(id = 1, name = "ABC")),
            nextEpisodeToAir = Episode(name = "Next Episode"),
            numberOfEpisodes = 10,
            numberOfSeasons = 2,
            originCountry = listOf("US"),
            originalLanguage = "en",
            originalName = "Original Name",
            overview = "Overview of the series",
            popularity = 9.0,
            posterPath = "/poster.jpg",
            productionCompanies = listOf(ProductionCompany(id = 1, name = "Production Company")),
            productionCountries = listOf(ProductionCountry(iso3166_1 = "US", name = "United States")),
            seasons = listOf(Season(seasonNumber = 1)),
            spokenLanguages = listOf(SpokenLanguage(englishName = "English", iso639_1 = "en", name = "English")),
            status = "In Progress",
            tagline = "Tagline of the series",
            type = "Scripted",
            voteAverage = 8.5,
            voteCount = 100,
            isFavorite = true,
        )

        assertEquals(true, series.adult)
        assertEquals("/backdrop.jpg", series.backdropPath)
        assertEquals(listOf(CreatedBy(name = "Creator")), series.createdBy)
        assertEquals(listOf(30, 45), series.episodeRunTime)
        assertEquals("2023-01-01", series.firstAirDate)
        assertEquals(listOf(Genre(id = 1, name = "Drama")), series.genres)
        assertEquals("http://example.com", series.homepage)
        assertEquals(123, series.id)
        assertEquals(true, series.inProduction)
        assertEquals(listOf("English", "Spanish"), series.languages)
        assertEquals("2023-12-31", series.lastAirDate)
        assertEquals(Episode(name = "Last Episode"), series.lastEpisodeToAir)
        assertEquals("Test Series", series.name)
        assertEquals(listOf(Network(id = 1, name = "ABC")), series.networks)
        assertEquals(Episode(name = "Next Episode"), series.nextEpisodeToAir)
        assertEquals(10, series.numberOfEpisodes)
        assertEquals(2, series.numberOfSeasons)
        assertEquals(listOf("US"), series.originCountry)
        assertEquals("en", series.originalLanguage)
        assertEquals("Original Name", series.originalName)
        assertEquals("Overview of the series", series.overview)
        assertEquals(9.0, series.popularity, 0.01)
        assertEquals("/poster.jpg", series.posterPath)
        assertEquals(listOf(ProductionCompany(id = 1, name = "Production Company")), series.productionCompanies)
        assertEquals(listOf(ProductionCountry(iso3166_1 = "US", name = "United States")), series.productionCountries)
        assertEquals(listOf(Season(seasonNumber = 1)), series.seasons)
        assertEquals(listOf(SpokenLanguage(englishName = "English", iso639_1 = "en", name = "English")), series.spokenLanguages)
        assertEquals("In Progress", series.status)
        assertEquals("Tagline of the series", series.tagline)
        assertEquals("Scripted", series.type)
        assertEquals(8.5, series.voteAverage, 0.01)
        assertEquals(100, series.voteCount)
        assertEquals(true, series.isFavorite)
    }

    @Test
    fun `convert Series to MediaIndex`() {
        val series = Series(
            adult = true,
            backdropPath = "/backdrop.jpg",
            createdBy = listOf(CreatedBy(name = "Creator")),
            episodeRunTime = listOf(30, 45),
            firstAirDate = "2023-01-01",
            genres = listOf(Genre(id = 1, name = "Drama")),
            homepage = "http://example.com",
            id = 123,
            inProduction = true,
            languages = listOf("English", "Spanish"),
            lastAirDate = "2023-12-31",
            lastEpisodeToAir = Episode(name = "Last Episode"),
            name = "Test Series",
            networks = listOf(Network(id = 1, name = "ABC")),
            nextEpisodeToAir = Episode(name = "Next Episode"),
            numberOfEpisodes = 10,
            numberOfSeasons = 2,
            originCountry = listOf("US"),
            originalLanguage = "en",
            originalName = "Original Name",
            overview = "Overview of the series",
            popularity = 9.0,
            posterPath = "/poster.jpg",
            productionCompanies = listOf(ProductionCompany(id = 1, name = "Production Company")),
            productionCountries = listOf(ProductionCountry(iso3166_1 = "US", name = "United States")),
            seasons = listOf(Season(seasonNumber = 1)),
            spokenLanguages = listOf(SpokenLanguage(englishName = "English", iso639_1 = "en", name = "English")),
            status = "In Progress",
            tagline = "Tagline of the series",
            type = "Scripted",
            voteAverage = 8.5,
            voteCount = 100,
            isFavorite = true,
        )

        val mediaIndex = series.asMediaIndexObject()
        assertEquals(true, mediaIndex.adult)
        assertEquals("/backdrop.jpg", mediaIndex.backdropPath)
        assertEquals(listOf(1), mediaIndex.genreIds)
        assertEquals(123, mediaIndex.id)
        assertEquals("tv", mediaIndex.mediaType)
        assertEquals("en", mediaIndex.originalLanguage)
        assertEquals("Original Name", mediaIndex.originalTitle)
        assertEquals("Overview of the series", mediaIndex.overview)
        assertEquals(9.0, mediaIndex.popularity, 0.0)
        assertEquals("/poster.jpg", mediaIndex.posterPath)
        assertEquals("2023-01-01", mediaIndex.releaseDate)
        assertEquals("Test Series", mediaIndex.title)
        assertEquals(false, mediaIndex.video)
        assertEquals(8.5, mediaIndex.voteAverage, 0.0)
        assertEquals(100, mediaIndex.voteCount)
        assertEquals(listOf("US"), mediaIndex.originCountry)
    }
}
