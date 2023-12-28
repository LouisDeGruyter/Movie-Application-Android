package com.example.moviesandseries.domain.credits

data class CreditsContainer(
    var cast: List<Credit?>,
    var crew: List<Credit?>,
    var id: Int
)

