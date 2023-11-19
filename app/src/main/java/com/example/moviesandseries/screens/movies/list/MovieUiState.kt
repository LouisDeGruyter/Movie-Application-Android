package com.example.moviesandseries.screens.movies.list

import com.example.moviesandseries.model.movie.MovieIndex

sealed interface MovieUiState{
    data class Success(
        val movieIndex: List<MovieIndex>
    ): MovieUiState

    object Loading: MovieUiState

    data class Error(val message: String): MovieUiState
}
