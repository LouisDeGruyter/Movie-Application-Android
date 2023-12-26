package com.example.moviesandseries.screens.components.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.paging.compose.LazyPagingItems
import com.example.moviesandseries.R
import com.example.moviesandseries.domain.series.SeriesIndex
import com.example.moviesandseries.screens.components.MediaCard

@Composable
fun LazySeriesRow(series: LazyPagingItems<SeriesIndex>, onSeriesClick: (seriesId: Int) -> Unit, modifier: Modifier) {
    val rowSpacing = dimensionResource(id = R.dimen.row_spacing)
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(rowSpacing, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        content = {
            items(series.itemCount) { seriesItem ->
                series[seriesItem]?.let { MediaCard(title = it.name, imagePath = it.posterPath ?: "", rating = it.voteAverage, onMediaClick = { onSeriesClick(it.id) }) }
            }
        },
    )
}
