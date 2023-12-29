package com.example.moviesandseries.screens.movies.detail

import androidx.paging.PagingData
import com.example.moviesandseries.domain.Collection.CollectionDetail
import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.domain.credits.CreditsContainer
import com.example.moviesandseries.domain.images.ImagesContainer
import com.example.moviesandseries.domain.movie.MovieDetail
import com.example.moviesandseries.model.videos.VideoContainer
import kotlinx.coroutines.flow.Flow

sealed interface MovieDetailUiState {
    data class Success(
        val movieDetail: MovieDetail,
        val images: ImagesContainer,
        val credits: CreditsContainer,
        val videos: VideoContainer,
        val collectionDetail: CollectionDetail?,
        val recommendedMedia: Flow<PagingData<MediaIndex>>,
    ) : MovieDetailUiState

    object Loading : MovieDetailUiState
    data class Error(val message: String) : MovieDetailUiState
}
