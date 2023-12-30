package com.example.domain

import com.example.moviesandseries.domain.SpokenLanguage
import org.junit.Assert.assertEquals
import org.junit.Test

class SpokenLanguageTest {

    @Test
    fun `create SpokenLanguage with default values`() {
        val spokenLanguage = SpokenLanguage()

        assertEquals("", spokenLanguage.englishName)
        assertEquals("", spokenLanguage.iso639_1)
        assertEquals("", spokenLanguage.name)
    }

    @Test
    fun `create SpokenLanguage with specific values`() {
        val spokenLanguage = SpokenLanguage(
            englishName = "English",
            iso639_1 = "en",
            name = "en-US",
        )

        assertEquals("English", spokenLanguage.englishName)
        assertEquals("en", spokenLanguage.iso639_1)
        assertEquals("en-US", spokenLanguage.name)
    }

}
