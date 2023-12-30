package com.example.domain.reviews

import com.example.moviesandseries.domain.reviews.Review
import com.example.moviesandseries.domain.reviews.ReviewContainer
import org.junit.Assert.assertEquals
import org.junit.Test

class ReviewContainerTest {

    @Test
    fun `create ReviewContainer with default values`() {
        val reviewContainer = ReviewContainer()

        assertEquals(0, reviewContainer.id)
        assertEquals(1, reviewContainer.page)
        assertEquals(emptyList<Review>(), reviewContainer.results)
        assertEquals(1, reviewContainer.totalPages)
        assertEquals(0, reviewContainer.totalResults)
    }

    @Test
    fun `create ReviewContainer with specific values`() {
        val review1 = Review(
            author = "John Doe",
            content = "A great movie!",
            id = "123",
            url = "https://example.com/review1",
        )

        val review2 = Review(
            author = "Jane Smith",
            content = "Fantastic cinematography!",
            id = "456",
            url = "https://example.com/review2",
        )

        val reviewContainer = ReviewContainer(
            id = 789,
            page = 2,
            results = listOf(review1, review2),
            totalPages = 3,
            totalResults = 20,
        )

        assertEquals(789, reviewContainer.id)
        assertEquals(2, reviewContainer.page)
        assertEquals(listOf(review1, review2), reviewContainer.results)
        assertEquals(3, reviewContainer.totalPages)
        assertEquals(20, reviewContainer.totalResults)
    }
}
