package com.example.moviesandseries.screens.movies.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviesandseries.screens.components.loading.LoadingMediaGrid
import com.example.moviesandseries.screens.components.movies.LazyGridMovies

private const val COLUMN_COUNT = 3

@Composable
fun MoviesScreen(movieViewModel: MovieViewModel, onMovieClick: (movieId: Int) -> Unit) {
    var movieUiState: MovieUiState = movieViewModel.movieUiState // if new variables get assigned to movieUiState
    var moviesByPage = movieViewModel.moviePager.collectAsLazyPagingItems()
    when (moviesByPage.loadState.refresh) {
        is LoadState.Loading -> LoadingMediaGrid(numberOfItems = 12, columns = COLUMN_COUNT)
        is LoadState.Error -> Text(text = "Error")
        else -> {
            LazyGridMovies(movies = moviesByPage, onMovieClick = onMovieClick, columns = COLUMN_COUNT)
        }
    }
}
