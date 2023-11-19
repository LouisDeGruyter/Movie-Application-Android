package com.example.moviesandseries.screens.series.detail.movies.list

import android.util.Log
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
import com.example.moviesandseries.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {
    var movieUiState: MovieUiState by mutableStateOf(MovieUiState.Loading)
        private set
    private var currentPage = 1

    init {
        getMovies(1)
    }

    fun getMovies(page:Int) {
        viewModelScope.launch {
            movieUiState = MovieUiState.Loading
            movieUiState = try {
                MovieUiState.Success(movieRepository.getMovies(page))
            } catch (e: Exception) {
                MovieUiState.Error(e.message ?: "An unknown error occured")
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MovieAndSeriesApplication)
                val movieRepository = application.container.movieRepository
                MovieViewModel(movieRepository)
            }
        }
    }
}