package com.example.domain.series

import com.example.moviesandseries.domain.series.CreatedBy
import org.junit.Assert.assertEquals
import org.junit.Test

class CreatedByTest {

    @Test
    fun `create CreatedBy with default values`() {
        val createdBy = CreatedBy()

        assertEquals("", createdBy.creditId)
        assertEquals(0, createdBy.gender)
        assertEquals(0, createdBy.id)
        assertEquals("", createdBy.name)
        assertEquals("", createdBy.profilePath)
    }

    @Test
    fun `create CreatedBy with specific values`() {
        val createdBy = CreatedBy(
            creditId = "abc123",
            gender = 1,
            id = 789,
            name = "John Doe",
            profilePath = "/profile.jpg",
        )

        assertEquals("abc123", createdBy.creditId)
        assertEquals(1, createdBy.gender)
        assertEquals(789, createdBy.id)
        assertEquals("John Doe", createdBy.name)
        assertEquals("/profile.jpg", createdBy.profilePath)
    }
}
