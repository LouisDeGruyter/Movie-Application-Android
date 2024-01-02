package com.example.moviesandseries.screens.movies.list

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
import com.example.moviesandseries.paging.movies.MoviePagingSource
import com.example.moviesandseries.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * ViewModel for managing and providing data related to movies.
 *
 * @param movieRepository The repository responsible for providing movie-related data.
 */
class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    /**
     * Flow representing the paginated data of movies. It utilizes the [MoviePagingSource] to load movies in pages.
     */
    val moviePager: Flow<PagingData<MediaIndex>> = Pager(PagingConfig(pageSize = 20)) {
        MoviePagingSource(movieRepository)
    }.flow.cachedIn(viewModelScope)

    companion object {
        /**
         * [ViewModelProvider.Factory] for creating instances of [MovieViewModel].
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MovieAndSeriesApplication)
                val movieRepository = application.container.movieRepository
                MovieViewModel(movieRepository)
            }
        }
    }
}
