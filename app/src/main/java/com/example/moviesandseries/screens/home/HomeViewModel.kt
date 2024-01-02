package com.example.moviesandseries.screens.home

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
import com.example.moviesandseries.paging.movies.MoviesInTheaterPagingSource
import com.example.moviesandseries.paging.movies.PopularMoviesPagingSource
import com.example.moviesandseries.paging.movies.TopRatedMoviesPagingSource
import com.example.moviesandseries.paging.movies.UpcomingMoviesPagingSource
import com.example.moviesandseries.paging.series.PopularSeriesPagingSource
import com.example.moviesandseries.paging.series.SeriesOnAirPagingSource
import com.example.moviesandseries.paging.series.SeriesOnAirTodayPagingSource
import com.example.moviesandseries.paging.series.TopRatedSeriesPagingSource
import com.example.moviesandseries.repository.MovieRepository
import com.example.moviesandseries.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow

/**
 * ViewModel for the Home screen.
 *
 * @param movieRepository Repository for movie-related data.
 * @param seriesRepository Repository for series-related data.
 */
class HomeViewModel(private val movieRepository: MovieRepository, private val seriesRepository: SeriesRepository) : ViewModel() {

    // Movie pagers
    val moviesInTheaterPager: Flow<PagingData<MediaIndex>> = Pager(PagingConfig(pageSize = 20)) {
        MoviesInTheaterPagingSource(movieRepository)
    }.flow.cachedIn(viewModelScope)

    val popularMoviesPagingSource: Flow<PagingData<MediaIndex>> = Pager(PagingConfig(pageSize = 20)) {
        PopularMoviesPagingSource(movieRepository)
    }.flow.cachedIn(viewModelScope)

    val topRatedMoviesPagingSource: Flow<PagingData<MediaIndex>> = Pager(PagingConfig(pageSize = 20)) {
        TopRatedMoviesPagingSource(movieRepository)
    }.flow.cachedIn(viewModelScope)

    val upcomingMoviesPagingSource: Flow<PagingData<MediaIndex>> = Pager(PagingConfig(pageSize = 20)) {
        UpcomingMoviesPagingSource(movieRepository)
    }.flow.cachedIn(viewModelScope)

    // Series pagers
    val popularSeriesPagingSource: Flow<PagingData<MediaIndex>> = Pager(PagingConfig(pageSize = 20)) {
        PopularSeriesPagingSource(seriesRepository)
    }.flow.cachedIn(viewModelScope)

    val topRatedSeriesPagingSource: Flow<PagingData<MediaIndex>> = Pager(PagingConfig(pageSize = 20)) {
        TopRatedSeriesPagingSource(seriesRepository)
    }.flow.cachedIn(viewModelScope)

    val onTheAirSeriesPagingSource: Flow<PagingData<MediaIndex>> = Pager(PagingConfig(pageSize = 20)) {
        SeriesOnAirPagingSource(seriesRepository)
    }.flow.cachedIn(viewModelScope)

    val airingTodaySeriesPagingSource: Flow<PagingData<MediaIndex>> = Pager(PagingConfig(pageSize = 20)) {
        SeriesOnAirTodayPagingSource(seriesRepository)
    }.flow.cachedIn(viewModelScope)

    companion object {
        /**
         * Factory for creating the HomeViewModel.
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MovieAndSeriesApplication)
                val movieRepository = application.container.movieRepository
                val seriesRepository = application.container.seriesRepository
                HomeViewModel(movieRepository, seriesRepository)
            }
        }
    }
}
