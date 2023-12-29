package com.example.moviesandseries.screens.movies.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviesandseries.screens.components.loading.LoadingMediaGrid
import com.example.moviesandseries.screens.components.index.LazyPagingMediaGrid

private const val COLUMN_COUNT = 3

@Composable
fun MoviesScreen(movieViewModel: MovieViewModel = viewModel(factory = MovieViewModel.Factory), onMovieClick: (movieId: Int) -> Unit) {
    val moviesByPage = movieViewModel.moviePager.collectAsLazyPagingItems()
    when (moviesByPage.loadState.refresh) {
        is LoadState.Loading -> LoadingMediaGrid(numberOfItems = 12, columns = COLUMN_COUNT)
        is LoadState.Error -> Text(text = "Error")
        else -> {
            LazyPagingMediaGrid(media = moviesByPage, onMediaClick = onMovieClick, columns = COLUMN_COUNT)
        }
    }
}
