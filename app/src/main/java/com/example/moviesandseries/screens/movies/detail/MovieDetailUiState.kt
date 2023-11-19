package com.example.moviesandseries.screens.movies.detail

import com.example.moviesandseries.model.movie.MovieDetail

sealed interface MovieDetailUiState{
    data class Success(
        val movieDetail: MovieDetail
    ): MovieDetailUiState

    object Loading: MovieDetailUiState
    data class Error(val message: String): MovieDetailUiState
}