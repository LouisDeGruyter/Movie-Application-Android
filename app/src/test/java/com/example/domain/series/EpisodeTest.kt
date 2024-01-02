package com.example.domain.series

import com.example.moviesandseries.domain.series.Episode
import org.junit.Assert.assertEquals
import org.junit.Test

class EpisodeTest {

    @Test
    fun `create EpisodeTest with default values`() {
        val episode = Episode(
            crew = crew.map { it.asDomainObject() },
            episodeType = episodeType,
            guestStars = guestStars.map { it.asDomainObject() })

        assertEquals("", episode.airDate)
        assertEquals(0, episode.episodeNumber)
        assertEquals(0, episode.id)
        assertEquals("", episode.name)
        assertEquals("", episode.overview)
        assertEquals("", episode.productionCode)
        assertEquals(0, episode.runtime)
        assertEquals(0, episode.seasonNumber)
        assertEquals(0, episode.showId)
        assertEquals("", episode.stillPath)
        assertEquals(0.0, episode.voteAverage, 0.01)
        assertEquals(0, episode.voteCount)
    }

    @Test
    fun `create EpisodeTest with specific values`() {
        val episode = Episode(
            airDate = "2023-01-01",
            episodeNumber = 5,
            id = 123,
            name = "The New Beginning",
            overview = "An exciting episode",
            productionCode = "ABC123",
            runtime = 45,
            seasonNumber = 2,
            showId = 456,
            stillPath = "/still.jpg",
            voteAverage = 8.5,
            voteCount = 100,
            crew = crew.map { it.asDomainObject() },
            episodeType = episodeType,
            guestStars = guestStars.map { it.asDomainObject() },
        )

        assertEquals("2023-01-01", episode.airDate)
        assertEquals(5, episode.episodeNumber)
        assertEquals(123, episode.id)
        assertEquals("The New Beginning", episode.name)
        assertEquals("An exciting episode", episode.overview)
        assertEquals("ABC123", episode.productionCode)
        assertEquals(45, episode.runtime)
        assertEquals(2, episode.seasonNumber)
        assertEquals(456, episode.showId)
        assertEquals("/still.jpg", episode.stillPath)
        assertEquals(8.5, episode.voteAverage, 0.01)
        assertEquals(100, episode.voteCount)
    }
}
