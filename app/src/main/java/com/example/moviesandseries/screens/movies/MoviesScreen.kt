package com.example.moviesandseries.screens.movies

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.moviesandseries.model.movie.MovieIndex

@Composable
fun MoviesScreen(movieUiState: MovieUiState) {
    var movies: List<MovieIndex> by remember { mutableStateOf(emptyList()) }
    when(movieUiState) {
        is MovieUiState.Loading -> Text(text = "Loading")
        is MovieUiState.Success -> { movies = movieUiState.movieIndex
            displayMovies(movies)
        }
        is MovieUiState.Error -> {
            Text(text = movieUiState.message)
        }
    }
}
@Composable
fun displayMovies(movies: List<MovieIndex>) {
    LazyColumn(content =
    {
        items(movies.size) { index ->
            Text(text = movies[index].title)
        }
    })
}