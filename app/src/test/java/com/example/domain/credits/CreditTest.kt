package com.example.domain.credits

import com.example.moviesandseries.domain.credits.Credit
import org.junit.Assert.assertEquals
import org.junit.Test

class CreditTest {

    @Test
    fun `create Credit with default values`() {
        val credit = Credit()

        assertEquals(false, credit.adult)
        assertEquals(0, credit.castId)
        assertEquals("", credit.character)
        assertEquals("", credit.creditId)
        assertEquals(0, credit.gender)
        assertEquals(0, credit.id)
        assertEquals("", credit.knownForDepartment)
        assertEquals("", credit.name)
        assertEquals(0, credit.order)
        assertEquals("", credit.originalName)
        assertEquals(0.0, credit.popularity, 0.0)
        assertEquals("", credit.profilePath)
    }

    @Test
    fun `create Credit with specific values`() {
        val credit = Credit(
            adult = true,
            castId = 123,
            character = "John Doe",
            creditId = "abc123",
            gender = 1,
            id = 456,
            knownForDepartment = "Acting",
            name = "Jane Doe",
            order = 1,
            originalName = "Jane Original",
            popularity = 99.9,
            profilePath = "/profile.jpg",
        )

        assertEquals(true, credit.adult)
        assertEquals(123, credit.castId)
        assertEquals("John Doe", credit.character)
        assertEquals("abc123", credit.creditId)
        assertEquals(1, credit.gender)
        assertEquals(456, credit.id)
        assertEquals("Acting", credit.knownForDepartment)
        assertEquals("Jane Doe", credit.name)
        assertEquals(1, credit.order)
        assertEquals("Jane Original", credit.originalName)
        assertEquals(99.9, credit.popularity, 0.0)
        assertEquals("/profile.jpg", credit.profilePath)
    }
}
