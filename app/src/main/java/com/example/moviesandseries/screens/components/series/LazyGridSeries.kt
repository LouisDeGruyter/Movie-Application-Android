package com.example.moviesandseries.screens.components.series

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import androidx.paging.compose.LazyPagingItems
import com.example.moviesandseries.R
import com.example.moviesandseries.domain.series.SeriesIndex
import com.example.moviesandseries.screens.components.MediaCard


@Composable
fun LazyGridSeries(series: LazyPagingItems<SeriesIndex>, onSeriesClick: (seriesId: Int) -> Unit, columns: Int) {
    val lazyGridState = rememberLazyGridState()
    val gridSpacing = dimensionResource(id = R.dimen.grid_spacing)
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        state = lazyGridState,
        contentPadding = PaddingValues(
            start = gridSpacing,
            end = gridSpacing,
            top = gridSpacing,
            bottom = gridSpacing,
        ),
        horizontalArrangement = Arrangement.spacedBy(gridSpacing, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(gridSpacing, Alignment.CenterVertically),
        content = {
            items(series.itemCount) { seriesItem ->
                series[seriesItem]?.let { MediaCard(title = it.name, imagePath = it.posterPath ?: "", rating = it.voteAverage, onMediaClick = { onSeriesClick(it.id) }) }
            }
        },
    )
}
