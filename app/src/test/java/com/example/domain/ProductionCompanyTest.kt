package com.example.domain

import com.example.moviesandseries.domain.ProductionCompany
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductionCompanyTest {

    @Test
    fun `create ProductionCompany with default values`() {
        val productionCompany = ProductionCompany()

        assertEquals(0, productionCompany.id)
        assertEquals("", productionCompany.logoPath)
        assertEquals("", productionCompany.name)
        assertEquals("", productionCompany.originCountry)
    }

    @Test
    fun `create ProductionCompany with specific values`() {
        val productionCompany = ProductionCompany(
            id = 123,
            logoPath = "/logo.jpg",
            name = "Production Co. Ltd.",
            originCountry = "US",
        )

        assertEquals(123, productionCompany.id)
        assertEquals("/logo.jpg", productionCompany.logoPath)
        assertEquals("Production Co. Ltd.", productionCompany.name)
        assertEquals("US", productionCompany.originCountry)
    }
}
