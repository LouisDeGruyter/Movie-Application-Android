package com.example.domain.reviews

import com.example.moviesandseries.domain.reviews.Author
import org.junit.Assert.assertEquals
import org.junit.Test

class AuthorTest {

    @Test
    fun `create Author with default values`() {
        val author = Author()

        assertEquals("", author.avatarPath)
        assertEquals("", author.name)
        assertEquals(0, author.rating)
        assertEquals("", author.username)
    }

    @Test
    fun `create Author with specific values`() {
        val author = Author(
            avatarPath = "/avatar.jpg",
            name = "John Doe",
            rating = 4,
            username = "john_doe",
        )

        assertEquals("/avatar.jpg", author.avatarPath)
        assertEquals("John Doe", author.name)
        assertEquals(4, author.rating)
        assertEquals("john_doe", author.username)
    }
}
