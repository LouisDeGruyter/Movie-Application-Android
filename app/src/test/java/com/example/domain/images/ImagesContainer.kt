package com.example.domain.images

import com.example.moviesandseries.domain.images.Image
import com.example.moviesandseries.domain.images.ImagesContainer
import org.junit.Assert.assertEquals
import org.junit.Test

class ImagesContainerTest {

    @Test
    fun `create ImagesContainer with default values`() {
        val imagesContainer = ImagesContainer()

        assertEquals(emptyList<Image>(), imagesContainer.backdrops)
        assertEquals(0, imagesContainer.id)
        assertEquals(emptyList<Image>(), imagesContainer.logos)
        assertEquals(emptyList<Image>(), imagesContainer.posters)
    }

    @Test
    fun `create ImagesContainer with specific values`() {
        val image1 = Image(aspectRatio = 1.78, filePath = "/backdrop1.jpg", height = 720, iso6391 = "en", voteAverage = 8.5, voteCount = 100, width = 1280)
        val image2 = Image(aspectRatio = 2.0, filePath = "/logo1.jpg", height = 200, iso6391 = "en", voteAverage = 7.0, voteCount = 50, width = 400)
        val image3 = Image(aspectRatio = 1.5, filePath = "/poster1.jpg", height = 1080, iso6391 = "en", voteAverage = 9.0, voteCount = 120, width = 1920)

        val imagesContainer = ImagesContainer(
            backdrops = listOf(image1),
            id = 42,
            logos = listOf(image2),
            posters = listOf(image3),
        )

        assertEquals(listOf(image1), imagesContainer.backdrops)
        assertEquals(42, imagesContainer.id)
        assertEquals(listOf(image2), imagesContainer.logos)
        assertEquals(listOf(image3), imagesContainer.posters)
    }
}
