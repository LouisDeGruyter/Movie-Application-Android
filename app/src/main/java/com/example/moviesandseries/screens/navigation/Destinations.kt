package com.example.moviesandseries.screens.navigation

enum class Destinations(val route: String) {
    Home("Home"),
    Movies("Movies"),
    Series("Series"),
    MovieDetails("MovieDetails"),
    SeriesDetail("SeriesDetail"),
    ;

    fun createRoute(id: String) = "$route/$id"
}