package com.example.moviesandseries.screens.series.detail

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
import com.example.moviesandseries.repository.SeriesRepository
import kotlinx.coroutines.launch

class SeriesDetailViewModel(private val seriesRepository: SeriesRepository) : ViewModel() {
    var seriesDetailUiState: SeriesDetailUiState by mutableStateOf(SeriesDetailUiState.Loading)
        private set
    fun getSeriesDetail(seriesId: Int) {
        viewModelScope.launch {
            seriesDetailUiState = SeriesDetailUiState.Loading
            seriesDetailUiState = try {
                SeriesDetailUiState.Success(seriesRepository.getSeriesDetail(seriesId))
            } catch (e: Exception) {
                SeriesDetailUiState.Error(e.message ?: "An unknown error occured")
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MovieAndSeriesApplication)
                val seriesRepository = application.container.seriesRepository
                SeriesDetailViewModel(seriesRepository)
            }
        }
    }
}
