package com.example.moviesandseries.screens.components.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.moviesandseries.domain.series.SeriesIndex
import com.example.moviesandseries.screens.components.series.SeriesCard

private val ROW_SPACING = 12.dp

@Composable
fun LazySeriesRow(series: LazyPagingItems<SeriesIndex>, onSeriesClick: (seriesId: Int) -> Unit, modifier: Modifier) {
    val lazyListState = rememberLazyListState()
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(ROW_SPACING, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        content = {
            items(series.itemCount) { serie ->
                serie?.let {
                    series[it]?.let { it1 -> SeriesCard(series = it1, onSeriesClick = onSeriesClick) }
                }
            }
        },
    )
}
