package com.example.moviesandseries.domain

/**
 * Data class representing spoken language information.
 *
 * @property englishName The English name of the spoken language.
 * @property iso639_1 The ISO 639-1 code for the spoken language.
 * @property name The name of the spoken language.
 */
data class SpokenLanguage(
    var englishName: String = "",
    var iso639_1: String = "",
    var name: String = "",
)
