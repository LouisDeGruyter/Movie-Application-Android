package com.example.domain.videos

import com.example.moviesandseries.model.videos.Video
import com.example.moviesandseries.model.videos.VideoContainer
import org.junit.Assert.assertEquals
import org.junit.Test

class VideoContainerTest {

    @Test
    fun `create VideoContainer with default values`() {
        val videoContainer = VideoContainer()

        assertEquals(0, videoContainer.id)
        assertEquals(emptyList<Video>(), videoContainer.results)
    }

    @Test
    fun `create VideoContainer with specific values`() {
        val video1 = Video(id = "abc123", name = "Video 1")
        val video2 = Video(id = "xyz456", name = "Video 2")

        val videoContainer = VideoContainer(
            id = 123,
            results = listOf(video1, video2),
        )

        assertEquals(123, videoContainer.id)
        assertEquals(2, videoContainer.results.size)
        assertEquals(video1, videoContainer.results[0])
        assertEquals(video2, videoContainer.results[1])
    }
}
