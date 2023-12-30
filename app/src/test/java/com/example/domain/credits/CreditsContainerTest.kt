package com.example.domain.credits

import com.example.moviesandseries.domain.credits.Credit
import com.example.moviesandseries.domain.credits.CreditsContainer
import org.junit.Assert.assertEquals
import org.junit.Test

class CreditsContainerTest {

    @Test
    fun `create CreditsContainer with default values`() {
        val creditsContainer = CreditsContainer()

        assertEquals(emptyList<Credit>(), creditsContainer.cast)
        assertEquals(emptyList<Credit>(), creditsContainer.crew)
        assertEquals(0, creditsContainer.id)
    }

    @Test
    fun `create CreditsContainer with specific values`() {
        val credit1 = Credit(adult = false, castId = 123, character = "John Doe", creditId = "abc123", gender = 1, id = 456, knownForDepartment = "Acting", name = "Jane Doe", order = 1, originalName = "Jane Original", popularity = 99.9, profilePath = "/profile1.jpg")
        val credit2 = Credit(adult = true, castId = 789, character = "Alice", creditId = "xyz789", gender = 2, id = 101, knownForDepartment = "Directing", name = "Bob", order = 2, originalName = "Bob Original", popularity = 88.8, profilePath = "/profile2.jpg")

        val creditsContainer = CreditsContainer(
            cast = listOf(credit1),
            crew = listOf(credit2),
            id = 42,
        )

        assertEquals(listOf(credit1), creditsContainer.cast)
        assertEquals(listOf(credit2), creditsContainer.crew)
        assertEquals(42, creditsContainer.id)
    }
}
