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


@Composable
fun SeriesScreen(seriesViewModel: SeriesViewModel = viewModel(factory = SeriesViewModel.Factory), onSeriesClick: (Int) -> Unit, navigationType: MoviesAndSeriesNavigationType) {
    var columns = 3
    when(navigationType){
        MoviesAndSeriesNavigationType.BOTTOM_NAVIGATION -> {
            columns = 3
        }
        MoviesAndSeriesNavigationType.NAVIGATION_RAIL -> {
            columns = 4
        }
        MoviesAndSeriesNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            columns = 6
        }
    }
    val seriesByPage = seriesViewModel.seriesPager.collectAsLazyPagingItems()
    when (seriesByPage.loadState.refresh) {
        is LoadState.Loading -> LoadingMediaGrid(numberOfItems = 12, columns = columns)
        is LoadState.Error -> Text(text = "Error")
        else -> {
            LazyPagingMediaGrid(media = seriesByPage, onMediaClick = onSeriesClick, columns = columns, modifier = Modifier.testTag("seriesGrid"))
        }
    }
}
