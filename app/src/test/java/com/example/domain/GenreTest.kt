package com.example.domain

import com.example.moviesandseries.domain.Genre
import org.junit.Assert.assertEquals
import org.junit.Test

class GenreTest {

    @Test
    fun `create Genre with default values`() {
        val genre = Genre()

        assertEquals(0, genre.id)
        assertEquals("", genre.name)
    }

    @Test
    fun `create Genre with specific values`() {
        val genre = Genre(
            id = 1,
            name = "Action",
        )

        assertEquals(1, genre.id)
        assertEquals("Action", genre.name)
    }
}
