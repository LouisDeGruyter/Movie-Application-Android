package com.example.moviesandseries.screens.series.detail

import com.example.moviesandseries.domain.series.Series

sealed interface SeriesDetailUiState {
    data class Success(
        val series: Series,
    ) : SeriesDetailUiState

    object Loading : SeriesDetailUiState
    data class Error(val message: String) : SeriesDetailUiState
}
