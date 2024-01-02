package com.example.moviesandseries.screens.series.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesandseries.MovieAndSeriesApplication
import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.paging.series.SeriesPagingSource
import com.example.moviesandseries.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow

/**
 * ViewModel for managing data related to the list of series, including fetching paginated series data.
 *
 * @param seriesRepository Repository for accessing series-related data.
 */
class SeriesViewModel(private val seriesRepository: SeriesRepository) : ViewModel() {

    // Flow representing paginated series data
    val seriesPager: Flow<PagingData<MediaIndex>> = Pager(PagingConfig(pageSize = 20)) {
        SeriesPagingSource(seriesRepository)
    }.flow.cachedIn(viewModelScope)

    companion object {
        /**
         * Factory for creating instances of [SeriesViewModel].
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MovieAndSeriesApplication)
                val seriesRepository = application.container.seriesRepository
                SeriesViewModel(seriesRepository)
            }
        }
    }
}
