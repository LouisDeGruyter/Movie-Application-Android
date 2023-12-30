package com.example.moviesandseries.domain.credits

data class Credit(
    var adult: Boolean = false,
    var castId: Int = 0,
    var character: String = "",
    var creditId: String = "",
    var gender: Int = 0,
    var id: Int = 0,
    var knownForDepartment: String = "",
    var name: String = "",
    var order: Int = 0,
    var originalName: String = "",
    var popularity: Double = 0.0,
    var profilePath: String = ""
)

