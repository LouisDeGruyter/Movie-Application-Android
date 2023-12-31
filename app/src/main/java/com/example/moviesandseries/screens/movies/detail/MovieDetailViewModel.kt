

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Suppress("EmptyMethod") // private set
class MovieDetailViewModel(private val movieRepository: MovieRepository, private val collectionRepository: CollectionRepository) : ViewModel() {
    var movieDetailApiState: MovieDetailApiState by mutableStateOf(MovieDetailApiState.Loading)
        private set
    private var _uiListMovieDetailState: MutableStateFlow<MovieDetailListState> = MutableStateFlow(MovieDetailListState())
    val uiListMovieDetailState: StateFlow<MovieDetailListState> = _uiListMovieDetailState.asStateFlow()
    private var currentId: Int by mutableStateOf(0)

    /**
     * Function to fetch movie details (including images, credits, videos, recommended movies and the collection to which the movie belongs) using the provided [movieId].
     *
     * @param movieId The ID of the movie to fetch details for.
     */
    fun getMovieDetail(movieId: Int) {
        currentId = movieId
        viewModelScope.launch {
            movieRepository.refreshMovie(movieId)
            movieDetailApiState = MovieDetailApiState.Loading
            try {
                val movieDetail = movieRepository.getMovieDetail(movieId).first()
                val images = movieRepository.getMovieImages(movieId)
                val credits = movieRepository.getMovieCredits(movieId)
                val videos = movieRepository.getMovieVideos(movieId)
                val collectionDetail = if (movieDetail.belongsToCollection.id != 0) collectionRepository.getCollectionDetail(movieDetail.belongsToCollection.id) else null
                val recommendedMovies: Flow<PagingData<MediaIndex>> = Pager(PagingConfig(pageSize = 20)) {
                    RecommendedMoviesPagingSource(movieRepository, movieId)
                }.flow.cachedIn(viewModelScope)
                val movieDetailListState = MovieDetailListState(movieDetail = movieDetail, images = images, credits = credits, videos = videos, collection = collectionDetail, recommendedMedia = recommendedMovies)
                _uiListMovieDetailState.update { movieDetailListState }
                movieDetailApiState = MovieDetailApiState.Success
            } catch (e: Exception) {
                movieDetailApiState = MovieDetailApiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    /**
     * Function to update the favorite status of the current movie.
     */
    fun updateFavorite() {
        viewModelScope.launch {
            movieRepository.updateFavorite(currentId, !_uiListMovieDetailState.value.movieDetail.isFavorite)
            _uiListMovieDetailState.update { it.copy(movieDetail = it.movieDetail.copy(isFavorite = !_uiListMovieDetailState.value.movieDetail.isFavorite)) }
        }
    }

    companion object {
        /**
         * Factory to create instances of [MovieDetailViewModel].
         */
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
