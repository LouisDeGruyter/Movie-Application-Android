package com.example.moviesandseries.screens.navigation

/**
 * Enum class representing different destinations/routes within the application.
 *
 * @param route The route string associated with each destination.
 */
enum class Destinations(val route: String) {
    Home("Home"),
    Movies("Movies"),
    Series("Series"),
    MovieDetails("MovieDetails"),
    SeriesDetail("SeriesDetail"),
    ;

    /**
     * Creates a route for the destination with the specified ID.
     *
     * @param id The ID to be included in the route.
     * @return The complete route string for the destination.
     */
    fun createRoute(id: String) = "$route/$id"
}
