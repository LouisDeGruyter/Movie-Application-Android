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

/**
 * Sealed interface representing the different states of the Series Detail API response.
 */
sealed interface SeriesDetailApiState {
    object Success : SeriesDetailApiState
    object Loading : SeriesDetailApiState
    data class Error(val message: String) : SeriesDetailApiState
}

/**
 * Data class representing the state of the Series Detail screen.
 *
 * @property seriesDetail The detailed information about the series.
 * @property images Container holding images related to the series.
 * @property credits Container holding credits information for the series.
 * @property videos Container holding video-related information for the series.
 * @property recommendedMedia Flow of PagingData representing recommended media for the series.
 * @property seasonDetail Detailed information about the currently selected season of the series.
 */
data class SeriesDetailListState(
    val seriesDetail: Series = Series(),
    val images: ImagesContainer = ImagesContainer(),
    val credits: CreditsContainer = CreditsContainer(),
    val videos: VideoContainer = VideoContainer(),
    val recommendedMedia: Flow<PagingData<MediaIndex>> = emptyFlow(),
    val seasonDetail: Season = Season(),
)
