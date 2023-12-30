package com.example.domain

import com.example.moviesandseries.domain.MediaIndex
import org.junit.Assert.assertEquals
import org.junit.Test

class MediaIndexTest {

    @Test
    fun `create MediaIndex with default values`() {
        val mediaIndex = MediaIndex()

        assertEquals(false, mediaIndex.adult)
        assertEquals("", mediaIndex.backdropPath)
        assertEquals(emptyList<Int>(), mediaIndex.genreIds)
        assertEquals(0, mediaIndex.id)
        assertEquals("", mediaIndex.mediaType)
        assertEquals("", mediaIndex.originalLanguage)
        assertEquals("", mediaIndex.originalTitle)
        assertEquals("", mediaIndex.overview)
        assertEquals(0.0, mediaIndex.popularity, 0.0)
        assertEquals("", mediaIndex.posterPath)
        assertEquals("", mediaIndex.releaseDate)
        assertEquals("", mediaIndex.title)
        assertEquals(false, mediaIndex.video)
        assertEquals(0.0, mediaIndex.voteAverage, 0.0)
        assertEquals(0, mediaIndex.voteCount)
        assertEquals(emptyList<String>(), mediaIndex.originCountry)
        assertEquals(false, mediaIndex.isFavorite)
    }

    @Test
    fun `create MediaIndex with specific values`() {
        val mediaIndex = MediaIndex(
            adult = true,
            backdropPath = "/backdrop.jpg",
            genreIds = listOf(1, 2, 3),
            id = 123,
            mediaType = "movie",
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "Overview of the media",
            popularity = 9.5,
            posterPath = "/poster.jpg",
            releaseDate = "2023-01-01",
            title = "Title of the media",
            video = true,
            voteAverage = 8.7,
            voteCount = 1000,
            originCountry = listOf("US", "CA"),
            isFavorite = true,
        )

        assertEquals(true, mediaIndex.adult)
        assertEquals("/backdrop.jpg", mediaIndex.backdropPath)
        assertEquals(listOf(1, 2, 3), mediaIndex.genreIds)
        assertEquals(123, mediaIndex.id)
        assertEquals("movie", mediaIndex.mediaType)
        assertEquals("en", mediaIndex.originalLanguage)
        assertEquals("Original Title", mediaIndex.originalTitle)
        assertEquals("Overview of the media", mediaIndex.overview)
        assertEquals(9.5, mediaIndex.popularity, 0.0)
        assertEquals("/poster.jpg", mediaIndex.posterPath)
        assertEquals("2023-01-01", mediaIndex.releaseDate)
        assertEquals("Title of the media", mediaIndex.title)
        assertEquals(true, mediaIndex.video)
        assertEquals(8.7, mediaIndex.voteAverage, 0.0)
        assertEquals(1000, mediaIndex.voteCount)
        assertEquals(listOf("US", "CA"), mediaIndex.originCountry)
        assertEquals(true, mediaIndex.isFavorite)
    }
}
