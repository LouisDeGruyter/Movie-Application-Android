package com.example.moviesandseries.screens.movies.detail

import com.example.moviesandseries.domain.credits.CreditsContainer
import com.example.moviesandseries.domain.images.ImagesContainer
import com.example.moviesandseries.domain.movie.MovieDetail

sealed interface MovieDetailUiState {
    data class Success(
        val movieDetail: MovieDetail,
        val images: ImagesContainer,
        val credits: CreditsContainer,
    ) : MovieDetailUiState

    object Loading : MovieDetailUiState
    data class Error(val message: String) : MovieDetailUiState
}
