package com.example.domain.series

import com.example.moviesandseries.domain.series.Season
import org.junit.Assert.assertEquals
import org.junit.Test

class SeasonTest {

    @Test
    fun `create Season with default values`() {
        val season = Season()

        assertEquals("", season.airDate)
        assertEquals(0, season.episodeCount)
        assertEquals(0, season.id)
        assertEquals("", season.name)
        assertEquals("", season.overview)
        assertEquals("", season.posterPath)
        assertEquals(0, season.seasonNumber)
        assertEquals(0.0, season.voteAverage, 0.01)
    }

    @Test
    fun `create Season with specific values`() {
        val season = Season(
            airDate = "2023-01-01",
            episodeCount = 10,
            id = 123,
            name = "Spring Season",
            overview = "Exciting episodes await!",
            posterPath = "/poster.jpg",
            seasonNumber = 2,
            voteAverage = 9.0,
            episodes = listOf(),
        )

        assertEquals("2023-01-01", season.airDate)
        assertEquals(10, season.episodeCount)
        assertEquals(123, season.id)
        assertEquals("Spring Season", season.name)
        assertEquals("Exciting episodes await!", season.overview)
        assertEquals("/poster.jpg", season.posterPath)
        assertEquals(2, season.seasonNumber)
        assertEquals(9.0, season.voteAverage, 0.01)
    }

    // Add more tests as needed
}
