package com.example.moviesandseries.screens.movies.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviesandseries.screens.components.index.LazyPagingMediaGrid
import com.example.moviesandseries.screens.components.loading.LoadingMediaGrid
import com.example.moviesandseries.util.MoviesAndSeriesNavigationType

/**
 * Composable function to display the list of movies based on the provided [movieViewModel] and [navigationType].
 *
 * @param movieViewModel The [MovieViewModel] to retrieve and manage movie data.
 * @param onMovieClick Callback function to handle movie item clicks.
 * @param navigationType The [MoviesAndSeriesNavigationType] indicating the current navigation style.
 */
@Composable
fun MoviesScreen(movieViewModel: MovieViewModel = viewModel(factory = MovieViewModel.Factory), onMovieClick: (movieId: Int) -> Unit, navigationType: MoviesAndSeriesNavigationType) {
    // Determine the number of columns based on the [navigationType]
    val columns = when (navigationType) {
        MoviesAndSeriesNavigationType.BOTTOM_NAVIGATION -> 3
        MoviesAndSeriesNavigationType.NAVIGATION_RAIL -> 4
        MoviesAndSeriesNavigationType.PERMANENT_NAVIGATION_DRAWER -> 6
    }

    // Collect lazy paging items for movies
    val moviesByPage = movieViewModel.moviePager.collectAsLazyPagingItems()

    // Handle different loading states
    when (moviesByPage.loadState.refresh) {
        is LoadState.Loading -> LoadingMediaGrid(numberOfItems = 12, columns = columns)
        is LoadState.Error -> Text(text = "Error")
        else -> {
            // Display movies using LazyPagingMediaGrid
            LazyPagingMediaGrid(media = moviesByPage, onMediaClick = onMovieClick, columns = columns, modifier = Modifier.testTag("moviesGrid"))
        }
    }
}
