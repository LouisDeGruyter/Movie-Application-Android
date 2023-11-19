package com.example.moviesandseries.screens.series

import com.example.moviesandseries.model.series.SeriesIndex

sealed interface SeriesUiState {
    data class Success(val series: List<SeriesIndex>) : SeriesUiState
    object Loading : SeriesUiState
    data class Error(val message: String) : SeriesUiState
}