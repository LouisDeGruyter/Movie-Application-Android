package com.example.moviesandseries.screens.series.detail

import com.example.moviesandseries.model.series.SeriesDetail

sealed interface SeriesDetailUiState{
    data class Success(
        val seriesDetail: SeriesDetail
    ): SeriesDetailUiState

    object Loading: SeriesDetailUiState
    data class Error(val message: String): SeriesDetailUiState
}