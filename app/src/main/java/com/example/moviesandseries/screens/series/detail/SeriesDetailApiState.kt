package com.example.moviesandseries.screens.series.detail

import androidx.paging.PagingData
import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.domain.credits.CreditsContainer
import com.example.moviesandseries.domain.images.ImagesContainer
import com.example.moviesandseries.domain.series.Season
import com.example.moviesandseries.domain.series.Series
import com.example.moviesandseries.model.videos.VideoContainer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

sealed interface SeriesDetailApiState {
    object Success : SeriesDetailApiState
    object Loading : SeriesDetailApiState
    data class Error(val message: String) : SeriesDetailApiState
}

data class SeriesDetailListState(
    val seriesDetail: Series = Series(),
    val images: ImagesContainer = ImagesContainer(),
    val credits: CreditsContainer = CreditsContainer(),
    val videos: VideoContainer = VideoContainer(),
    val recommendedMedia: Flow<PagingData<MediaIndex>> = emptyFlow(),
    val seasonDetail: Season = Season(),

)
