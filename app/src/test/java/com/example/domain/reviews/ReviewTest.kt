package com.example.domain.reviews

import com.example.moviesandseries.domain.reviews.Author
import com.example.moviesandseries.domain.reviews.Review
import org.junit.Assert.assertEquals
import org.junit.Test

class ReviewTest {

    @Test
    fun `create Review with default values`() {
        val review = Review()

        assertEquals("", review.author)
        assertEquals(Author(), review.authorDetails)
        assertEquals("", review.content)
        assertEquals("", review.createdAt)
        assertEquals("", review.id)
        assertEquals("", review.updatedAt)
        assertEquals("", review.url)
    }

    @Test
    fun `create Review with specific values`() {
        val author = Author(avatarPath = "/avatar.jpg", name = "John Doe", rating = 4, username = "john_doe")

        val review = Review(
            author = "John Doe",
            authorDetails = author,
            content = "A great movie!",
            createdAt = "2023-01-01",
            id = "123",
            updatedAt = "2023-01-02",
            url = "https://example.com/review",
        )

        assertEquals("John Doe", review.author)
        assertEquals(author, review.authorDetails)
        assertEquals("A great movie!", review.content)
        assertEquals("2023-01-01", review.createdAt)
        assertEquals("123", review.id)
        assertEquals("2023-01-02", review.updatedAt)
        assertEquals("https://example.com/review", review.url)
    }

    // Add more tests as needed
}
