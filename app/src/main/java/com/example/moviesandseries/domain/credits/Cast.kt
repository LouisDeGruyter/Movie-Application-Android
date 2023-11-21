package com.example.moviesandseries.domain.credits

data class Cast(
    var adult: Boolean,
    var castId: Int,
    var character: String,
    var creditId: String,
    var gender: Int,
    var id: Int,
    var knownForDepartment: String,
    var name: String,
    var order: Int,
    var originalName: String,
    var popularity: Double,
    var profilePath: String
)

