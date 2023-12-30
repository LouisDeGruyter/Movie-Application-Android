package com.example.domain.series

import com.example.moviesandseries.domain.series.Network
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkTest {

    @Test
    fun `create Network with default values`() {
        val network = Network()

        assertEquals(0, network.id)
        assertEquals("", network.logoPath)
        assertEquals("", network.name)
        assertEquals("", network.originCountry)
    }

    @Test
    fun `create Network with specific values`() {
        val network = Network(
            id = 123,
            logoPath = "/logo.png",
            name = "ABC Network",
            originCountry = "US",
        )

        assertEquals(123, network.id)
        assertEquals("/logo.png", network.logoPath)
        assertEquals("ABC Network", network.name)
        assertEquals("US", network.originCountry)
    }
}
