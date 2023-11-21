package com.example.moviesandseries.screens.movies.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviesandseries.domain.movie.MovieDetail
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MovieDetailsScreen(movieId: String?, movieDetailViewModel: MovieDetailViewModel = viewModel(factory = MovieDetailViewModel.Factory)) {
    when (val movieDetailUiState = movieDetailViewModel.movieDetailUiState) {
        is MovieDetailUiState.Success -> {
            displayMovieDetail(movieDetail = (movieDetailUiState as MovieDetailUiState.Success).movieDetail)
        }
        is MovieDetailUiState.Loading -> {
            LoadingAnimation()
        }
        is MovieDetailUiState.Error -> {
            ErrorText(message = (movieDetailUiState as MovieDetailUiState.Error).message)
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
fun displayMovieDetail(movieDetail: MovieDetail) {
    // Use animateContentSize to apply a simple fade-in/fade-out animation
    Column(
        modifier = Modifier
            .animateContentSize()
            .padding(8.dp),
    ) {
        Text(text = movieDetail.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        // Add more UI components for other details
    }
}

@Composable
fun LoadingAnimation() {
    // Placeholder for loading animation
    CircularProgressIndicator(modifier = Modifier.fillMaxSize())
}

@Composable
fun ErrorText(message: String) {
    // Placeholder for error message
    Text(text = message, color = Color.Red, modifier = Modifier.fillMaxSize())
}
