package com.example.moviesandseries.screens.movies.list

import androidx.paging.PagingData
import com.example.moviesandseries.model.movie.MovieIndex
import kotlinx.coroutines.flow.Flow

sealed interface MovieUiState{
    data class Success(
        val movieIndex: Flow<PagingData<MovieIndex>>
    ): MovieUiState

    object Loading: MovieUiState

    data class Error(val message: String): MovieUiState
}
