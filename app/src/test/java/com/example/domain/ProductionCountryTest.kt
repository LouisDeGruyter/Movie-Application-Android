package com.example.domain

import com.example.moviesandseries.domain.ProductionCountry
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductionCountryTest {

    @Test
    fun `create ProductionCountry with default values`() {
        val productionCountry = ProductionCountry()

        assertEquals("", productionCountry.iso3166_1)
        assertEquals("", productionCountry.name)
    }

    @Test
    fun `create ProductionCountry with specific values`() {
        val productionCountry = ProductionCountry(
            iso3166_1 = "US",
            name = "United States",
        )

        assertEquals("US", productionCountry.iso3166_1)
        assertEquals("United States", productionCountry.name)
    }
}
