package com.example.moviesandseries.screens.movies.detail

import com.example.moviesandseries.domain.Collection.CollectionDetail
import com.example.moviesandseries.domain.credits.CreditsContainer
import com.example.moviesandseries.domain.images.ImagesContainer
import com.example.moviesandseries.domain.movie.MovieDetail
import com.example.moviesandseries.model.videos.VideoContainer

sealed interface MovieDetailUiState {
    data class Success(
        val movieDetail: MovieDetail,
        val images: ImagesContainer,
        val credits: CreditsContainer,
        val videos: VideoContainer,
        val collectionDetail: CollectionDetail?,
    ) : MovieDetailUiState

    object Loading : MovieDetailUiState
    data class Error(val message: String) : MovieDetailUiState
}
