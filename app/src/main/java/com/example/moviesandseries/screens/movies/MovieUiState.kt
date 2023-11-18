package com.example.moviesandseries.screens.movies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moviesandseries.MovieAndSeriesApplication
import com.example.moviesandseries.model.movie.MovieIndex
import com.example.moviesandseries.repository.MovieRepository
import kotlinx.coroutines.launch

sealed interface MovieUiState{
    data class Success(
        val movieIndex: List<MovieIndex>
    ): MovieUiState

    object Loading: MovieUiState
    data class Error(val message: String): MovieUiState
}
