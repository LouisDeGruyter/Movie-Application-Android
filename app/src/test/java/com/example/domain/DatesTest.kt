package com.example.domain

import com.example.moviesandseries.domain.Dates
import org.junit.Assert.assertEquals
import org.junit.Test

class DatesTest {

    @Test
    fun `create Dates with default values`() {
        val dates = Dates()

        assertEquals("", dates.maximum)
        assertEquals("", dates.minimum)
    }

    @Test
    fun `create Dates with specific values`() {
        val dates = Dates(
            maximum = "2023-12-31",
            minimum = "2023-01-01",
        )

        assertEquals("2023-12-31", dates.maximum)
        assertEquals("2023-01-01", dates.minimum)
    }
}
