package com.example.moviesandseries.domain.credits

data class CreditsContainer(
    var cast: List<Credit?> = listOf(),
    var crew: List<Credit?> = listOf(),
    var id: Int = 0,
)

