package com.example.moviesandseries.screens.movies.detail

import androidx.paging.PagingData
import com.example.moviesandseries.domain.Collection
import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.domain.credits.CreditsContainer
import com.example.moviesandseries.domain.images.ImagesContainer
import com.example.moviesandseries.domain.movie.Movie
import com.example.moviesandseries.model.videos.VideoContainer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/**
 * Sealed interface representing different states for the MovieDetail API call.
 */
sealed interface MovieDetailApiState {
    object Success : MovieDetailApiState
    object Loading : MovieDetailApiState
    data class Error(val message: String) : MovieDetailApiState
}

/**
 * Data class representing the state of the MovieDetail screen.
 *
 * @param movieDetail The detailed information about the movie.
 * @param images Container holding images related to the movie.
 * @param credits Container holding credits information for the movie.
 * @param videos Container holding video information related to the movie.
 * @param collection Information about the collection to which the movie belongs.
 * @param recommendedMedia Flow of [PagingData] representing recommended media for the movie.
 */
data class MovieDetailListState(
    val movieDetail: Movie = Movie(),
    val images: ImagesContainer = ImagesContainer(),
    val credits: CreditsContainer = CreditsContainer(),
    val videos: VideoContainer = VideoContainer(),
    val collection: Collection? = null,
    val recommendedMedia: Flow<PagingData<MediaIndex>> = emptyFlow(),
)
