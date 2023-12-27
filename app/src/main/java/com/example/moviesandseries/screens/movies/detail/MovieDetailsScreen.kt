package com.example.moviesandseries.screens.movies.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviesandseries.screens.components.detail.movie.MovieDetailComposable

@Composable
fun MovieDetailsScreen(movieId: String?, movieDetailViewModel: MovieDetailViewModel = viewModel(factory = MovieDetailViewModel.Factory), backButton: @Composable (Modifier) -> Unit) {
    when (val movieDetailUiState = movieDetailViewModel.movieDetailUiState) {
        is MovieDetailUiState.Success -> {
            MovieDetailComposable(movie = movieDetailUiState.movieDetail, backButton = backButton, images = movieDetailUiState.images)
        }
        is MovieDetailUiState.Loading -> {
            LoadingAnimation()
        }
        is MovieDetailUiState.Error -> {
            ErrorText(message = movieDetailUiState.message)
        }
    }

    // Trigger the getMovieDetail function when the screen is initially displayed
    LaunchedEffect(key1 = movieId) {
        if (movieId != null) {
            movieDetailViewModel.getMovieDetail(movieId.toInt())
        }
    }
}

@Composable
fun LoadingAnimation() {
    CircularProgressIndicator(modifier = Modifier.fillMaxSize())
}

@Composable
fun ErrorText(message: String) {
    // Placeholder for error message
    Text(text = message, color = Color.Red, modifier = Modifier.fillMaxSize())
}
