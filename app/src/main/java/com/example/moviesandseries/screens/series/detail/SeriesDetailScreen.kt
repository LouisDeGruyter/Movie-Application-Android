package com.example.moviesandseries.screens.series.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviesandseries.domain.series.SeriesDetail

@Composable
fun SeriesDetailScreen(seriesId: String?, seriesDetailViewModel: SeriesDetailViewModel = viewModel(factory = SeriesDetailViewModel.Factory)) {
    when (val seriesDetailUiState = seriesDetailViewModel.seriesDetailUiState) {
        is SeriesDetailUiState.Success -> {
            DisplaySeriesDetail(seriesDetail = seriesDetailUiState.seriesDetail)
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
            seriesDetailViewModel.getSeriesDetail(seriesId.toInt())
        }
    }
}

@Composable
fun DisplaySeriesDetail(seriesDetail: SeriesDetail) {
    Text(text = seriesDetail.name)
}
