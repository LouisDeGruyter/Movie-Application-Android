package com.example.moviesandseries.screens.series.list

import com.example.moviesandseries.domain.series.SeriesIndex

sealed interface SeriesUiState {
    data class Success(val series: List<SeriesIndex>) : SeriesUiState
    object Loading : SeriesUiState
    data class Error(val message: String) : SeriesUiState
}
