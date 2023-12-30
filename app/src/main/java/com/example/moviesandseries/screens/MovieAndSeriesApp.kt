package com.example.moviesandseries.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviesandseries.screens.components.BackButton
import com.example.moviesandseries.screens.home.HomeScreen
import com.example.moviesandseries.screens.movies.detail.MovieDetailsScreen
import com.example.moviesandseries.screens.movies.list.MoviesScreen
import com.example.moviesandseries.screens.series.detail.SeriesDetailScreen
import com.example.moviesandseries.screens.series.list.SeriesScreen
import com.example.templateapplication.screens.appBar.MyBottomAppBar
import com.example.templateapplication.screens.appBar.MyTopAppBar

enum class Destinations(val route: String) {
    Home("Home"),
    Movies("Movies"),
    Series("Series"),
    MovieDetails("MovieDetails"),
    SeriesDetail("SeriesDetail"),
    ;

    fun createRoute(id: String) = "$route/$id"
}

@Composable
fun MovieAndSeriesApp() {
    // navigation
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route
    val currentEnumDestination: Destinations? = currentDestination?.let { route ->
        enumValues<Destinations>().find {
            it.route == route.substring(
                startIndex = 0,
                endIndex = if ("/" in route) route.indexOf("/") else route.length,
            )
        }
    }
    val currentPage = currentEnumDestination?.route ?: ""
    val noTopAppbarRoutes: List<String> = listOf(
        Destinations.MovieDetails.route,
        Destinations.SeriesDetail.route,
    )
    val navigateBack: () -> Unit = { navController.popBackStack() }

    Scaffold(
        topBar = {
            if (currentPage !in noTopAppbarRoutes) {
                MyTopAppBar(currentpage = currentPage) {
                    if (currentDestination != Destinations.Home.route) {
                        BackButton(onBackPressed = navigateBack)
                    }
                }
            }
        },
        bottomBar = {
            MyBottomAppBar(
                onHome = { navController.popBackStack(Destinations.Home.route, inclusive = false) },
                onMovies = { navController.navigate(Destinations.Movies.route) },
                onSeries = { navController.navigate(Destinations.Series.route) },
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destinations.Home.route,
            Modifier.padding(innerPadding),
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
                MoviesScreen(onMovieClick = ::onMovieClick)
            }
            composable(Destinations.Series.route) {
                SeriesScreen(onSeriesClick = ::onSeriesClick)
            }
            composable("${Destinations.MovieDetails.route}/{id}") {
                    backStackEntry ->
                MovieDetailsScreen(movieId = backStackEntry.arguments?.getString("id"), backButton = { BackButton(onBackPressed = navigateBack) }, onMovieClick = ::onMovieClick, onSeriesClick = ::onSeriesClick)
            }
            composable("${Destinations.SeriesDetail.route}/{id}") {
                    backStackEntry ->
                SeriesDetailScreen(seriesId = backStackEntry.arguments?.getString("id"))
            }
        }
    }
}
