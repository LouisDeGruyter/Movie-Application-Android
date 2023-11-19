package com.example.moviesandseries.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviesandseries.screens.home.HomeScreen
import com.example.moviesandseries.screens.movies.detail.MovieDetailsScreen
import com.example.moviesandseries.screens.movies.list.MovieViewModel
import com.example.moviesandseries.screens.movies.list.MoviesScreen
import com.example.moviesandseries.screens.series.detail.SeriesDetailScreen
import com.example.moviesandseries.screens.series.detail.SeriesDetailViewModel
import com.example.moviesandseries.screens.movies.detail.MovieDetailViewModel
import com.example.moviesandseries.screens.series.list.SeriesScreen
import com.example.moviesandseries.screens.series.list.SeriesViewModel
import com.example.templateapplication.screens.appBar.MyBottomAppBar
import com.example.templateapplication.screens.appBar.MyTopAppBar

enum class Destinations(val route: String){
    Home("Home"),
    Movies("Movies"),
    Series("Series"),
    MovieDetails("MovieDetails"),
    SeriesDetail("SeriesDetail");

    fun createRoute(id: String) = "$route/$id"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieAndSeriesApp() {
    //viewmodels
    val movieViewModel: MovieViewModel = viewModel(factory = MovieViewModel.Factory)
    val seriesViewModel: SeriesViewModel = viewModel(factory = SeriesViewModel.Factory)
    //viewmodel details
    val seriesDetailViewModel: SeriesDetailViewModel = viewModel(factory = SeriesDetailViewModel.Factory)
    val movieDetailViewModel: MovieDetailViewModel = viewModel(factory = MovieDetailViewModel.Factory)
    //navigation
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route
    val currentEnumDestination:Destinations? = currentDestination?.let { route ->
        enumValues<Destinations>().find { it.route == route }
    }
    val currentPage = currentEnumDestination?.route ?: "Home"
    Scaffold (
        topBar= {
            MyTopAppBar(currentpage = currentPage) {

                if (currentDestination != Destinations.Home.route) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Localized description")
                    }
                }
            }
        },
        bottomBar = {
            MyBottomAppBar(
                onHome = { navController.popBackStack(Destinations.Home.route, inclusive= false) },
                onMovies = { navController.navigate(Destinations.Movies.route) },
                onSeries = { navController.navigate(Destinations.Series.route) },
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destinations.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Destinations.Home.route) {
                HomeScreen()
            }
            composable(Destinations.Movies.route) {
                MoviesScreen(movieViewModel = movieViewModel, onMovieClick = { movieId: Int ->
                    navController.navigate(Destinations.MovieDetails.createRoute(movieId.toString()))
                })
            }
            composable(Destinations.Series.route) {
                SeriesScreen (seriesUiState= seriesViewModel.seriesUiState,onSeriesClick= {seriesId: Int ->
                    navController.navigate(Destinations.SeriesDetail.createRoute(seriesId.toString()))
                })
            }
            composable("${Destinations.MovieDetails.route}/{id}") {
                backStackEntry -> MovieDetailsScreen(viewModel=movieDetailViewModel,movieId = backStackEntry.arguments?.getString("id"))
            }
            composable("${Destinations.SeriesDetail.route}/{id}") {
                backStackEntry -> SeriesDetailScreen(viewModel=seriesDetailViewModel,seriesId = backStackEntry.arguments?.getString("id"))
            }
        }
    }
}

