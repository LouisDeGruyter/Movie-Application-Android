package com.example.moviesandseries.screens.series.list

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
 * Composable function for displaying a grid of series based on the provided [SeriesViewModel].
 *
 * @param seriesViewModel ViewModel for managing series-related data.
 * @param onSeriesClick Callback for handling series item clicks.
 * @param navigationType Type of navigation, affecting the number of columns in the grid.
 */
@Composable
fun SeriesScreen(
    seriesViewModel: SeriesViewModel = viewModel(factory = SeriesViewModel.Factory),
    onSeriesClick: (Int) -> Unit,
    navigationType: MoviesAndSeriesNavigationType,
) {
    // Determine the number of columns based on the navigation type
    val columns = when (navigationType) {
        MoviesAndSeriesNavigationType.BOTTOM_NAVIGATION -> 3
        MoviesAndSeriesNavigationType.NAVIGATION_RAIL -> 4
        MoviesAndSeriesNavigationType.PERMANENT_NAVIGATION_DRAWER -> 6
    }

    // Collect LazyPagingItems representing series from the ViewModel
    val seriesByPage = seriesViewModel.seriesPager.collectAsLazyPagingItems()

    // Handle different load states and display appropriate UI
    when (seriesByPage.loadState.refresh) {
        is LoadState.Loading -> LoadingMediaGrid(numberOfItems = 12, columns = columns)
        is LoadState.Error -> Text(text = "Error")
        else -> {
            // Display LazyPagingMediaGrid with series items
            LazyPagingMediaGrid(
                media = seriesByPage,
                onMediaClick = onSeriesClick,
                columns = columns,
                modifier = Modifier.testTag("seriesGrid"),
            )
        }
    }
}
