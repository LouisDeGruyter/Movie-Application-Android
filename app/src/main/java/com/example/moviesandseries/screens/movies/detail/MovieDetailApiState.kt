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

sealed interface MovieDetailApiState {
    object Success : MovieDetailApiState

    object Loading : MovieDetailApiState
    data class Error(val message: String) : MovieDetailApiState
}
data class MovieDetailListState(
    val movieDetail: Movie = Movie(),
    val images: ImagesContainer = ImagesContainer(),
    val credits: CreditsContainer = CreditsContainer(),
    val videos: VideoContainer = VideoContainer(),
    val collection: Collection? = null,
    val recommendedMedia: Flow<PagingData<MediaIndex>> = emptyFlow(),
)
