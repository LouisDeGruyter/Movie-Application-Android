package com.example.moviesandseries.screens.series.detail

sealed interface SeriesDetailUiState{
    data class Success(
        val seriesDetail: SeriesDetail
    ): SeriesDetailUiState

    object Loading: SeriesDetailUiState
    data class Error(val message: String): SeriesDetailUiState
}