package com.example.moviesandseries.domain.credits

data class Crew(
    var adult: Boolean,
    var creditId: String,
    var department: String,
    var gender: Int,
    var id: Int,
    var job: String,
    var knownForDepartment: String,
    var name: String,
    var originalName: String,
    var popularity: Double,
    var profilePath: String
)

