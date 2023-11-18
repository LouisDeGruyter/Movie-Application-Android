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
import com.example.moviesandseries.screens.movies.MovieViewModel
import com.example.moviesandseries.screens.movies.MoviesScreen
import com.example.moviesandseries.screens.series.SeriesScreen
import com.example.templateapplication.screens.appBar.MyBottomAppBar
import com.example.templateapplication.screens.appBar.MyTopAppBar

enum class Destinations{
    Home,
    Movies,
    Series
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieAndSeriesApp() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route
    val movieViewModel: MovieViewModel = viewModel(factory = MovieViewModel.Factory)

    Scaffold (
        topBar= {
            MyTopAppBar(currentpage = currentDestination?.let { it1 -> Destinations.valueOf(it1).name } ?: "Home") {

                if (currentDestination != Destinations.Home.name) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Localized description")
                    }
                }
            }
        },
        bottomBar = {
            MyBottomAppBar(
                onHome = { navController.popBackStack(Destinations.Home.name, inclusive= false) },
                onMovies = { navController.navigate(Destinations.Movies.name) },
                onSeries = { navController.navigate(Destinations.Series.name) },
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destinations.Home.name,
            Modifier.padding(innerPadding)
        ) {
            composable(Destinations.Home.name) {
                HomeScreen()
            }
            composable(Destinations.Movies.name) {
                MoviesScreen(movieUiState = movieViewModel.movieUiState)
            }
            composable(Destinations.Series.name) {
                SeriesScreen()
            }
        }
    }
}

