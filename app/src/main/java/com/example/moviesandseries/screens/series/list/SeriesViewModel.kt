package com.example.moviesandseries.screens.series.list

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
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
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer.ThrowException

class SeriesViewModel(private val seriesRepository: SeriesRepository):ViewModel() {
    var seriesUiState: SeriesUiState by mutableStateOf(SeriesUiState.Loading)
        private set
    init {
        getSeries(1)
    }


    fun getSeries(page:Int ){
        viewModelScope.launch {
            seriesUiState = SeriesUiState.Loading
            seriesUiState = try {
                SeriesUiState.Success(seriesRepository.getSeries(page))
            }
            catch (e: Exception) {
                Log.e("SeriesViewModel", "getSeries: ${e.message}", e)
                Log.e("SeriesViewModel", "getSeries: ${e.toString()}", e)
                SeriesUiState.Error(e.message ?: "An unknown error occured")

            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MovieAndSeriesApplication)
                val seriesRepository = application.container.seriesRepository
                SeriesViewModel(seriesRepository)
            }
        }
    }
}