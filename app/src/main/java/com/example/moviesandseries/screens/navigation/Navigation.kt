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

@Composable
fun navComponent(navController: NavHostController, modifier: Modifier = Modifier, navigationType: MoviesAndSeriesNavigationType) {
    val navigateBack: () -> Unit = { navController.popBackStack() }
    NavHost(
        navController = navController,
        startDestination = Destinations.Home.route,
        modifier = modifier,
    ) {
        fun onMovieClick(movieId: Int) {
            navController.navigate(Destinations.MovieDetails.createRoute(movieId.toString()))
        }
        fun onSeriesClick(seriesId: Int) {
            navController.navigate(Destinations.SeriesDetail.createRoute(seriesId.toString()))
        }
        composable(Destinations.Home.route) {
            HomeScreen(onMovieClick = ::onMovieClick, onSeriesClick = ::onSeriesClick)
        }
        composable(Destinations.Movies.route) {
            MoviesScreen(onMovieClick = ::onMovieClick, navigationType = navigationType)
        }
        composable(Destinations.Series.route) {
            SeriesScreen(onSeriesClick = ::onSeriesClick , navigationType = navigationType)
        }
        composable("${Destinations.MovieDetails.route}/{id}") {
                backStackEntry ->
            MovieDetailsScreen(movieId = backStackEntry.arguments?.getString("id"), backButton = { BackButton(onBackPressed = navigateBack) }, onMovieClick = ::onMovieClick, onSeriesClick = ::onSeriesClick , navigationType = navigationType)
        }
        composable("${Destinations.SeriesDetail.route}/{id}") {
                backStackEntry ->
            SeriesDetailScreen(seriesId = backStackEntry.arguments?.getString("id"))
        }
    }
}
