package com.example.moviesandseries.screens.movies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moviesandseries.MovieAndSeriesApplication
import com.example.moviesandseries.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {
    var movieUiState: MovieUiState by mutableStateOf(MovieUiState.Loading)
        private set

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            movieUiState = MovieUiState.Loading
            movieUiState = try {
                MovieUiState.Success(movieRepository.getMovies())
            } catch (e: Exception) {
                MovieUiState.Error(e.message ?: "An unknown error occured")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieAndSeriesApplication)
                val movieRepository = application.container.movieRepository
                MovieViewModel(movieRepository)
            }
        }
    }
}