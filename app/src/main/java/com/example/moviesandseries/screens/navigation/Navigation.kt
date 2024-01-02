package com.example.moviesandseries.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviesandseries.screens.components.BackButton
import com.example.moviesandseries.screens.home.HomeScreen
import com.example.moviesandseries.screens.movies.detail.MovieDetailsScreen
import com.example.moviesandseries.screens.movies.list.MoviesScreen
import com.example.moviesandseries.screens.series.detail.SeriesDetailScreen
import com.example.moviesandseries.screens.series.list.SeriesScreen
import com.example.moviesandseries.util.MoviesAndSeriesNavigationType

/**
 * Composable function representing the main navigation component of the Movies and Series app.
 *
 * @param navController The navigation controller used for navigating between destinations.
 * @param modifier The modifier for customization of the NavHost.
 * @param navigationType The type of navigation used in the app.
 */
@Composable
fun navComponent(navController: NavHostController, modifier: Modifier = Modifier, navigationType: MoviesAndSeriesNavigationType) {
    // Callback function to navigate back
    val navigateBack: () -> Unit = { navController.popBackStack() }

    // NavHost for handling navigation
    NavHost(
        navController = navController,
        startDestination = Destinations.Home.route,
        modifier = modifier,
    ) {
        // Callback functions for handling item clicks in HomeScreen
        fun onMovieClick(movieId: Int) {
            navController.navigate(Destinations.MovieDetails.createRoute(movieId.toString()))
        }
        fun onSeriesClick(seriesId: Int) {
            navController.navigate(Destinations.SeriesDetail.createRoute(seriesId.toString()))
        }

        // Composables for each destination
        composable(Destinations.Home.route) {
            HomeScreen(onMovieClick = ::onMovieClick, onSeriesClick = ::onSeriesClick)
        }
        composable(Destinations.Movies.route) {
            MoviesScreen(onMovieClick = ::onMovieClick, navigationType = navigationType)
        }
        composable(Destinations.Series.route) {
            SeriesScreen(onSeriesClick = ::onSeriesClick, navigationType = navigationType)
        }
        composable("${Destinations.MovieDetails.route}/{id}") { backStackEntry ->
            MovieDetailsScreen(
                movieId = backStackEntry.arguments?.getString("id"),
                backButton = { BackButton(onBackPressed = navigateBack) },
                onMovieClick = ::onMovieClick,
                onSeriesClick = ::onSeriesClick,
                navigationType = navigationType,
            )
        }
        composable("${Destinations.SeriesDetail.route}/{id}") { backStackEntry ->
            SeriesDetailScreen(
                seriesId = backStackEntry.arguments?.getString("id"),
                backButton = { BackButton(onBackPressed = navigateBack) },
                onMovieClick = ::onMovieClick,
                onSeriesClick = ::onSeriesClick,
                navigationType = navigationType,
            )
        }
    }
}
