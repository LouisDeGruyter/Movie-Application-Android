package com.example.domain.images

import com.example.moviesandseries.domain.images.Image
import org.junit.Assert.assertEquals
import org.junit.Test

class ImageTest {

    @Test
    fun `create Image with default values`() {
        val image = Image()

        assertEquals(0.0, image.aspectRatio, 0.0)
        assertEquals("", image.filePath)
        assertEquals(0, image.height)
        assertEquals("", image.iso6391)
        assertEquals(0.0, image.voteAverage, 0.0)
        assertEquals(0, image.voteCount)
        assertEquals(0, image.width)
    }

    @Test
    fun `create Image with specific values`() {
        val image = Image(
            aspectRatio = 1.78,
            filePath = "/image.jpg",
            height = 720,
            iso6391 = "en",
            voteAverage = 8.5,
            voteCount = 100,
            width = 1280,
        )

        assertEquals(1.78, image.aspectRatio, 0.0)
        assertEquals("/image.jpg", image.filePath)
        assertEquals(720, image.height)
        assertEquals("en", image.iso6391)
        assertEquals(8.5, image.voteAverage, 0.0)
        assertEquals(100, image.voteCount)
        assertEquals(1280, image.width)
    }

    // Add more tests as needed
}
