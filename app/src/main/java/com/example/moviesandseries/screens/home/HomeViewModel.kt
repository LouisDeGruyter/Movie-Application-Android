package com.example.moviesandseries.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moviesandseries.MovieAndSeriesApplication
import com.example.moviesandseries.repository.MovieRepository
import com.example.moviesandseries.repository.SeriesRepository

class HomeViewModel (private val movieRepository: MovieRepository, private val seriesRepository: SeriesRepository): ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MovieAndSeriesApplication)
                val movieRepository = application.container.movieRepository
                val seriesRepository = application.container.seriesRepository
                HomeViewModel(movieRepository,seriesRepository)
            }
        }
    }


}