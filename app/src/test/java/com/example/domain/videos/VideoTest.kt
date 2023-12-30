package com.example.domain.videos

import com.example.moviesandseries.model.videos.Video
import org.junit.Assert.assertEquals
import org.junit.Test

class VideoTest {

    @Test
    fun `create Video with default values`() {
        val video = Video()

        assertEquals("", video.id)
        assertEquals("", video.iso31661)
        assertEquals("", video.iso6391)
        assertEquals("", video.key)
        assertEquals("", video.name)
        assertEquals(false, video.official)
        assertEquals("", video.publishedAt)
        assertEquals("", video.site)
        assertEquals(0, video.size)
        assertEquals("", video.type)
    }

    @Test
    fun `create Video with specific values`() {
        val video = Video(
            id = "abc123",
            iso31661 = "US",
            iso6391 = "en",
            key = "xyz456",
            name = "Test Video",
            official = true,
            publishedAt = "2023-01-01",
            site = "YouTube",
            size = 720,
            type = "Trailer",
        )

        assertEquals("abc123", video.id)
        assertEquals("US", video.iso31661)
        assertEquals("en", video.iso6391)
        assertEquals("xyz456", video.key)
        assertEquals("Test Video", video.name)
        assertEquals(true, video.official)
        assertEquals("2023-01-01", video.publishedAt)
        assertEquals("YouTube", video.site)
        assertEquals(720, video.size)
        assertEquals("Trailer", video.type)
    }
}
