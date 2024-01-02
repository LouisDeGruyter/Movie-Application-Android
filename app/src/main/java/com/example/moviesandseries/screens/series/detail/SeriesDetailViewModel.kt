package com.example.moviesandseries.screens.series.detail

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
import com.example.moviesandseries.paging.series.RecommendedSeriesPagingSource
import com.example.moviesandseries.repository.SeasonRepository
import com.example.moviesandseries.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SeriesDetailViewModel(private val seriesRepository: SeriesRepository, private val seasonRepository: SeasonRepository) : ViewModel() {
    var seriesDetailApiState: SeriesDetailApiState by mutableStateOf(SeriesDetailApiState.Loading)
        private set
    private var _uiListSeriesDetailState: MutableStateFlow<SeriesDetailListState> = MutableStateFlow(SeriesDetailListState())
    val uiListSeriesDetailState: StateFlow<SeriesDetailListState> = _uiListSeriesDetailState.asStateFlow()
    private var currentId: Int by mutableStateOf(0)
    fun getSeriesDetail(seriesId: Int) {
        currentId = seriesId
        viewModelScope.launch {
            seriesRepository.refreshSeries(seriesId)
            seriesDetailApiState = SeriesDetailApiState.Loading
            try {
                val seriesDetail = seriesRepository.getSeriesDetail(seriesId).first()
                val images = seriesRepository.getSeriesImages(seriesId)
                val credits = seriesRepository.getSeriesCredits(seriesId)
                val videos = seriesRepository.getSeriesVideos(seriesId)
                val season = seasonRepository.getSeasonDetail(seriesId, 1)
                val recommendedMedia: Flow<PagingData<MediaIndex>> = Pager(PagingConfig(pageSize = 20)) {
                    RecommendedSeriesPagingSource(seriesRepository, seriesId)
                }.flow.cachedIn(viewModelScope)
                val seriesDetailListState = SeriesDetailListState(seriesDetail = seriesDetail, images = images, credits = credits, videos = videos, seasonDetail = season, recommendedMedia = recommendedMedia)
                _uiListSeriesDetailState.update { seriesDetailListState }
                seriesDetailApiState = SeriesDetailApiState.Success
            } catch (e: Exception) {
                SeriesDetailApiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    fun getSeason(seasonNumber: Int) {
        viewModelScope.launch {
            val season = seasonRepository.getSeasonDetail(currentId, seasonNumber)
            _uiListSeriesDetailState.update { it.copy(seasonDetail = season) }
        }
    }

    fun updateFavorite() {
        viewModelScope.launch {
            seriesRepository.updateFavorite(currentId, !_uiListSeriesDetailState.value.seriesDetail.isFavorite)
            _uiListSeriesDetailState.update { it.copy(seriesDetail = it.seriesDetail.copy(isFavorite = !_uiListSeriesDetailState.value.seriesDetail.isFavorite)) }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MovieAndSeriesApplication)
                val seriesRepository = application.container.seriesRepository
                val seasonRepository = application.container.seasonRepository
                SeriesDetailViewModel(seriesRepository, seasonRepository)
            }
        }
    }
}
