package com.example.moviesandseries.screens.series.detail.movies.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.moviesandseries.model.movie.MovieIndex
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MoviesScreen(movieViewModel: MovieViewModel, onMovieClick: (movieId:Int) -> Unit) {
    val movieUiState = movieViewModel.movieUiState
    var movies: List<MovieIndex> by remember { mutableStateOf(emptyList()) }

    when(movieUiState) {
        is MovieUiState.Loading -> Text(text = "Loading")
        is MovieUiState.Success -> { movies = movieUiState.movieIndex
            displayMovies(movies, onMovieClick)
        }

        is MovieUiState.Error -> {
            Text(text = movieUiState.message)
        }

    }
}



@Composable
fun displayMovies(movies: List<MovieIndex>, onMovieClick: (movieId: Int) -> Unit) {
    Text(text = movies.size.toString())
    val lazyGridState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state=lazyGridState,
        content = {
            items(movies.size) { index ->
                MovieItem(movie = movies[index], onMovieClick = onMovieClick)
            }
        },


    )
}

@Composable
fun MovieItem(movie: MovieIndex, onMovieClick: (Int) -> Unit) {
    // Customize this composable based on how you want to display each movie item
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = { onMovieClick(movie.id) })
    ) {
        // You can use Image, CoilImage, or any other composable to display the movie poster
        Text(text = movie.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        // Add other movie details as needed
    }
}

