package com.example.moviesandseries.screens.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.material.icons.filled.Movie
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.moviesandseries.R

enum class MoviesAndSeriesAppNavigation(@StringRes val title: Int, val route: String, val contentDescription: String, val icon: ImageVector) {
    Home(title = R.string.menu_home, route = Destinations.Home.route, contentDescription = "Navigate to the home page", icon = Icons.Filled.Home),
    Movies(title = R.string.menu_movies, route = Destinations.Movies.route, contentDescription = "Navigate to movies page", icon = Icons.Filled.LocalMovies),
    Series(title = R.string.menu_series, route = Destinations.Series.route, contentDescription = "Navigate to series page", icon = Icons.Filled.Movie),
}