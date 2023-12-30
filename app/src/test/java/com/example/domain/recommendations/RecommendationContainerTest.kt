package com.example.domain.recommendations

import com.example.moviesandseries.domain.recommendations.RecommendationContainer
import com.example.moviesandseries.domain.recommendations.RecommendationMedia
import org.junit.Assert.assertEquals
import org.junit.Test

class RecommendationContainerTest {

    @Test
    fun `create RecommendationContainer with default values`() {
        val recommendationContainer = RecommendationContainer()

        assertEquals(1, recommendationContainer.page)
        assertEquals(emptyList<RecommendationMedia>(), recommendationContainer.results)
        assertEquals(1, recommendationContainer.totalPages)
        assertEquals(0, recommendationContainer.totalResults)
    }

    @Test
    fun `create RecommendationContainer with specific values`() {
        val recommendationMedia1 = RecommendationMedia(id = 1, title = "Recommendation 1")
        val recommendationMedia2 = RecommendationMedia(id = 2, title = "Recommendation 2")

        val recommendationContainer = RecommendationContainer(
            page = 2,
            results = listOf(recommendationMedia1, recommendationMedia2),
            totalPages = 3,
            totalResults = 15,
        )

        assertEquals(2, recommendationContainer.page)
        assertEquals(listOf(recommendationMedia1, recommendationMedia2), recommendationContainer.results)
        assertEquals(3, recommendationContainer.totalPages)
        assertEquals(15, recommendationContainer.totalResults)
    }
}
