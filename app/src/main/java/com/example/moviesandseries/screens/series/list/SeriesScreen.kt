package com.example.moviesandseries.screens.series.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.moviesandseries.domain.series.SeriesIndex

@Composable
fun SeriesScreen(seriesUiState: SeriesUiState, onSeriesClick: (Int) -> Unit) {
    when (seriesUiState) {
        is SeriesUiState.Success -> {
            displaySeries(seriesUiState.series, onSeriesClick)
        }
        is SeriesUiState.Loading -> {
            Text(text = "Loading")
        }
        is SeriesUiState.Error -> {
            Text(text = seriesUiState.message)
        }
    }
}

@Composable
fun displaySeries(series: List<SeriesIndex>, onSeriesClick: (Int) -> Unit) {
    LazyColumn(
        content =
        {
            items(series.size) { index ->
                Text(
                    text = series[index].name + " " + series[index].id,
                    modifier = androidx.compose.ui.Modifier.clickable {
                        onSeriesClick(series[index].id)
                    },
                )
            }
        },
    )
}
