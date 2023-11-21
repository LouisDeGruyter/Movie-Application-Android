package com.example.moviesandseries.domain.credits

data class CreditsContainer(
    var cast: List<Cast?>,
    var crew: List<Crew?>,
    var id: Int
)

