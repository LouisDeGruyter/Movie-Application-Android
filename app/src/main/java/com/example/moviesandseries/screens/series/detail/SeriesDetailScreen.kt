package com.example.moviesandseries.screens.series.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.moviesandseries.domain.series.SeriesDetail

@Composable
fun SeriesDetailScreen(seriesId: String?, viewModel: SeriesDetailViewModel) {
    when (val seriesDetailUiState = viewModel.seriesDetailUiState) {
        is SeriesDetailUiState.Success -> {
            displaySeriesDetail(seriesDetail = seriesDetailUiState.seriesDetail)
        }
        is SeriesDetailUiState.Loading -> {
            Text(text = "Loading")
        }
        is SeriesDetailUiState.Error -> {
            Text(text = seriesDetailUiState.message)
        }
    }
    LaunchedEffect(key1 = seriesId) {
        if (seriesId != null) {
            viewModel.getSeriesDetail(seriesId.toInt())
        }
    }
}

@Composable
fun displaySeriesDetail(seriesDetail: SeriesDetail) {
    Text(text = seriesDetail.name)
}
