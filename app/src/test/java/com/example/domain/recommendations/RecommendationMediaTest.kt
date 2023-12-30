package com.example.domain.recommendations

import com.example.moviesandseries.domain.recommendations.RecommendationMedia
import com.example.moviesandseries.domain.recommendations.asMediaIndexObject
import org.junit.Assert.assertEquals
import org.junit.Test

class RecommendationMediaTest {

    @Test
    fun `create RecommendationMedia with default values`() {
        val recommendationMedia = RecommendationMedia()

        assertEquals(false, recommendationMedia.adult)
        assertEquals("", recommendationMedia.backdropPath)
        assertEquals(emptyList<Int>(), recommendationMedia.genreIds)
        assertEquals(0, recommendationMedia.id)
        assertEquals("", recommendationMedia.mediaType)
        assertEquals("", recommendationMedia.originalLanguage)
        assertEquals("", recommendationMedia.originalTitle)
        assertEquals("", recommendationMedia.overview)
        assertEquals(0.0, recommendationMedia.popularity, 0.0)
        assertEquals("", recommendationMedia.posterPath)
        assertEquals("", recommendationMedia.releaseDate)
        assertEquals("", recommendationMedia.title)
        assertEquals(false, recommendationMedia.video)
        assertEquals(0.0, recommendationMedia.voteAverage, 0.0)
        assertEquals(0, recommendationMedia.voteCount)
    }

    @Test
    fun `create RecommendationMedia with specific values`() {
        val recommendationMedia = RecommendationMedia(
            adult = true,
            backdropPath = "/backdrop.jpg",
            genreIds = listOf(1, 2),
            id = 42,
            mediaType = "movie",
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "Overview",
            popularity = 9.5,
            posterPath = "/poster.jpg",
            releaseDate = "2023-01-01",
            title = "Recommendation Title",
            video = true,
            voteAverage = 8.0,
            voteCount = 200,
        )

        assertEquals(true, recommendationMedia.adult)
        assertEquals("/backdrop.jpg", recommendationMedia.backdropPath)
        assertEquals(listOf(1, 2), recommendationMedia.genreIds)
        assertEquals(42, recommendationMedia.id)
        assertEquals("movie", recommendationMedia.mediaType)
        assertEquals("en", recommendationMedia.originalLanguage)
        assertEquals("Original Title", recommendationMedia.originalTitle)
        assertEquals("Overview", recommendationMedia.overview)
        assertEquals(9.5, recommendationMedia.popularity, 0.0)
        assertEquals("/poster.jpg", recommendationMedia.posterPath)
        assertEquals("2023-01-01", recommendationMedia.releaseDate)
        assertEquals("Recommendation Title", recommendationMedia.title)
        assertEquals(true, recommendationMedia.video)
        assertEquals(8.0, recommendationMedia.voteAverage, 0.0)
        assertEquals(200, recommendationMedia.voteCount)
    }

    @Test
    fun `convert RecommendationMedia to MediaIndex`() {
        val recommendationMedia = RecommendationMedia(
            id = 1,
            title = "Recommendation 1",
            genreIds = listOf(1, 2),
            mediaType = "movie",
        )

        val mediaIndex = recommendationMedia.asMediaIndexObject()

        assertEquals(false, mediaIndex.adult)
        assertEquals("", mediaIndex.backdropPath)
        assertEquals(listOf(1, 2), mediaIndex.genreIds)
        assertEquals(1, mediaIndex.id)
        assertEquals("movie", mediaIndex.mediaType)
        assertEquals("", mediaIndex.originalLanguage)
        assertEquals("", mediaIndex.originalTitle)
        assertEquals("", mediaIndex.overview)
        assertEquals(0.0, mediaIndex.popularity, 0.0)
        assertEquals("", mediaIndex.posterPath)
        assertEquals("", mediaIndex.releaseDate)
        assertEquals("Recommendation 1", mediaIndex.title)
        assertEquals(false, mediaIndex.video)
        assertEquals(0.0, mediaIndex.voteAverage, 0.0)
        assertEquals(0, mediaIndex.voteCount)
        assertEquals(null, mediaIndex.originCountry)
    }
}
