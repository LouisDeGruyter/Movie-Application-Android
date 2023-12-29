package com.example.moviesandseries.screens.movies.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import com.example.moviesandseries.paging.movies.RecommendedMoviesPagingSource
import com.example.moviesandseries.repository.CollectionRepository
import com.example.moviesandseries.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
class MovieDetailViewModel(private val movieRepository: MovieRepository, private val collectionRepository: CollectionRepository) : ViewModel() {
    var movieDetailUiState: MovieDetailUiState by mutableStateOf(MovieDetailUiState.Loading)
        private set

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            movieDetailUiState = MovieDetailUiState.Loading
            movieDetailUiState = try {
                val movieDetail = movieRepository.getMovieDetail(movieId)
                val images = movieRepository.getMovieImages(movieId)
                val credits = movieRepository.getMovieCredits(movieId)
                val videos = movieRepository.getMovieVideos(movieId)
                val collectionDetail = movieDetail.belongsToCollection?.let { collectionRepository.getCollectionDetail(it.id) }
                val recommendedMovies: Flow<PagingData<MediaIndex>> = Pager(PagingConfig(pageSize = 20)) {
                    RecommendedMoviesPagingSource(movieRepository, movieId)
                }.flow.cachedIn(viewModelScope)
                MovieDetailUiState.Success(movieDetail = movieDetail, images = images, credits = credits, videos = videos, collectionDetail = collectionDetail, recommendedMedia = recommendedMovies)
            } catch (e: Exception) {
                MovieDetailUiState.Error(e.message ?: "An unknown error occured")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MovieAndSeriesApplication)
                val movieRepository = application.container.movieRepository
                val collectionRepository = application.container.collectionRepository
                MovieDetailViewModel(movieRepository, collectionRepository)
            }
        }
    }
}
