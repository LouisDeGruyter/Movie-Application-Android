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

private const val COLUMN_COUNT = 3

@Composable
fun SeriesScreen(seriesViewModel: SeriesViewModel = viewModel(factory = SeriesViewModel.Factory), onSeriesClick: (Int) -> Unit) {
    val seriesByPage = seriesViewModel.seriesPager.collectAsLazyPagingItems()
    when (seriesByPage.loadState.refresh) {
        is LoadState.Loading -> LoadingMediaGrid(numberOfItems = 12, columns = COLUMN_COUNT)
        is LoadState.Error -> Text(text = "Error")
        else -> {
            LazyPagingMediaGrid(media = seriesByPage, onMediaClick = onSeriesClick, columns = COLUMN_COUNT, modifier = Modifier.testTag("seriesGrid"))
        }
    }
}
