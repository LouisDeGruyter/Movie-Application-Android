package com.example.viewmodel

import com.example.moviesandseries.domain.Genre
import com.example.moviesandseries.domain.ProductionCompany
import com.example.moviesandseries.domain.ProductionCountry
import com.example.moviesandseries.domain.RecommendationContainer
import com.example.moviesandseries.domain.SpokenLanguage
import com.example.moviesandseries.domain.credits.CreditsContainer
import com.example.moviesandseries.domain.images.ImagesContainer
import com.example.moviesandseries.domain.series.CreatedBy
import com.example.moviesandseries.domain.series.Episode
import com.example.moviesandseries.domain.series.Network
import com.example.moviesandseries.domain.series.Season
import com.example.moviesandseries.domain.series.Series
import com.example.moviesandseries.model.videos.VideoContainer
import com.example.moviesandseries.repository.SeasonRepository
import com.example.moviesandseries.repository.SeriesRepository
import com.example.moviesandseries.screens.series.detail.SeriesDetailApiState
import com.example.moviesandseries.screens.series.detail.SeriesDetailListState
import com.example.moviesandseries.screens.series.detail.SeriesDetailViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SeriesDetailViewModelTest {
    private lateinit var viewModel: SeriesDetailViewModel
    private var seriesRepository: SeriesRepository = mockk()
    private var seasonRepository: SeasonRepository = mockk()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    val season: Season = Season(
        airDate = "2023-01-01",
        id = 123,
        name = "Spring Season",
        overview = "Exciting episodes await!",
        posterPath = "/poster.jpg",
        seasonNumber = 2,
        voteAverage = 9.0,
        episodes = listOf(),
    )

    val seriesDetail = Series(
        adult = true,
        backdropPath = "/backdrop.jpg",
        createdBy = listOf(CreatedBy(name = "Creator")),
        episodeRunTime = listOf(30, 45),
        firstAirDate = "2023-01-01",
        genres = listOf(Genre(id = 1, name = "Drama")),
        homepage = "http://example.com",
        id = 1,
        inProduction = true,
        languages = listOf("en"),
        lastAirDate = "2023-01-01",
        lastEpisodeToAir = Episode(
            airDate = "2023-01-01",
            episodeNumber = 1,
            id = 1,
            name = "Episode 1",
            overview = "Overview",
            productionCode = "123",
            seasonNumber = 1,
            stillPath = "/still.jpg",
            voteAverage = 9.0,
            voteCount = 1,
        ),
        name = "Test Series",
        networks = listOf(Network(name = "Network")),
        nextEpisodeToAir = Episode(
            airDate = "2023-01-01",
            episodeNumber = 1,
            id = 1,
            name = "Episode 1",
            overview = "Overview",
            productionCode = "123",
            seasonNumber = 1,
            stillPath = "/still.jpg",
            voteAverage = 9.0,
            voteCount = 1,
        ),
        numberOfEpisodes = 1,
        numberOfSeasons = 1,
        originCountry = listOf("US"),
        originalLanguage = "en",
        originalName = "Test Series",
        overview = "Overview",
        popularity = 1.0,
        posterPath = "/poster.jpg",
        productionCompanies = listOf(ProductionCompany(name = "Company")),
        productionCountries = listOf(ProductionCountry(name = "US")),
        seasons = listOf(season),
        spokenLanguages = listOf(SpokenLanguage(name = "English")),
        status = "Status",
        tagline = "Tagline",
        type = "Type",
        voteAverage = 9.0,
        voteCount = 1,
    )

    @Test
    fun `get seriesDetail should update uiListSeriesDetailState and set seriesDetailApiState to succes`() =
        testScope.runTest {
            coEvery { seriesRepository.getSeriesDetail(seriesDetail.id) } returns flowOf(seriesDetail)
            coEvery { seasonRepository.getSeasonDetail(seriesDetail.id, 1) } returns season
            coEvery { seriesRepository.refreshSeries(seriesDetail.id) } returns Unit
            coEvery { seriesRepository.getSeriesImages(seriesDetail.id) } returns ImagesContainer()
            coEvery { seriesRepository.getSeriesCredits(seriesDetail.id) } returns CreditsContainer()
            coEvery { seriesRepository.getRecommendedSeries(seriesDetail.id, page = 1) } returns RecommendationContainer()
            coEvery { seriesRepository.getSeriesVideos(seriesDetail.id) } returns VideoContainer()

            viewModel = SeriesDetailViewModel(seriesRepository, seasonRepository)
            viewModel.getSeriesDetail(seriesDetail.id)
            val collectCompleted = CompletableDeferred<Unit>()
            var uiListSeriesDetailState: SeriesDetailListState? = null
            val job = launch {
                viewModel.uiListSeriesDetailState.collect {
                    uiListSeriesDetailState = it
                    collectCompleted.complete(Unit)
                }
            }

            collectCompleted.await()

            advanceUntilIdle()

            assert(viewModel.seriesDetailApiState is SeriesDetailApiState.Success)
            val shouldBeSeriesDetailListState = SeriesDetailListState(
                seriesDetail = seriesDetail,
                seasonDetail = season,
                images = ImagesContainer(),
                credits = CreditsContainer(),
                videos = VideoContainer(),
            )
            assert(uiListSeriesDetailState?.copy(recommendedMedia = emptyFlow()) == shouldBeSeriesDetailListState)
            job.cancel()
        }

    @Test
    fun `updateFavorite should update uiListSeriesDetailState`() =
        testScope.runTest {
            coEvery { seriesRepository.updateFavorite(seriesDetail.id, !seriesDetail.isFavorite) } returns Unit
            coEvery { seriesRepository.getSeriesDetail(seriesDetail.id) } returns flowOf(seriesDetail)
            coEvery { seasonRepository.getSeasonDetail(seriesDetail.id, 1) } returns season
            coEvery { seriesRepository.refreshSeries(seriesDetail.id) } returns Unit
            coEvery { seriesRepository.getSeriesImages(seriesDetail.id) } returns ImagesContainer()
            coEvery { seriesRepository.getSeriesCredits(seriesDetail.id) } returns CreditsContainer()
            coEvery { seriesRepository.getRecommendedSeries(seriesDetail.id, page = 1) } returns RecommendationContainer()
            coEvery { seriesRepository.getSeriesVideos(seriesDetail.id) } returns VideoContainer()

            viewModel = SeriesDetailViewModel(seriesRepository, seasonRepository)
            viewModel.getSeriesDetail(seriesDetail.id)
            val collectCompleted = CompletableDeferred<Unit>()
            var uiListSeriesDetailState: SeriesDetailListState? = null
            val job = launch {
                viewModel.uiListSeriesDetailState.collect {
                    uiListSeriesDetailState = it
                    collectCompleted.complete(Unit)
                }
            }

            collectCompleted.await()

            advanceUntilIdle()

            viewModel.updateFavorite()

            advanceUntilIdle()

            assert(uiListSeriesDetailState?.copy(recommendedMedia = emptyFlow()) == SeriesDetailListState(seriesDetail = seriesDetail.copy(isFavorite = !seriesDetail.isFavorite), seasonDetail = season, images = ImagesContainer(), credits = CreditsContainer(), videos = VideoContainer()))
            job.cancel()
        }

    @Test
    fun `getSeason should update uiListSeriesDetailState`() =
        testScope.runTest {
            coEvery { seriesRepository.getSeriesDetail(seriesDetail.id) } returns flowOf(seriesDetail)
            coEvery { seasonRepository.getSeasonDetail(seriesDetail.id, 1) } returns season
            coEvery { seriesRepository.refreshSeries(seriesDetail.id) } returns Unit
            coEvery { seriesRepository.getSeriesImages(seriesDetail.id) } returns ImagesContainer()
            coEvery { seriesRepository.getSeriesCredits(seriesDetail.id) } returns CreditsContainer()
            coEvery { seriesRepository.getRecommendedSeries(seriesDetail.id, page = 1) } returns RecommendationContainer()
            coEvery { seriesRepository.getSeriesVideos(seriesDetail.id) } returns VideoContainer()

            viewModel = SeriesDetailViewModel(seriesRepository, seasonRepository)
            viewModel.getSeriesDetail(seriesDetail.id)
            val collectCompleted = CompletableDeferred<Unit>()
            var uiListSeriesDetailState: SeriesDetailListState? = null
            val job = launch {
                viewModel.uiListSeriesDetailState.collect {
                    uiListSeriesDetailState = it
                    collectCompleted.complete(Unit)
                }
            }

            collectCompleted.await()

            advanceUntilIdle()

            viewModel.getSeason(1)

            advanceUntilIdle()

            assert(uiListSeriesDetailState?.copy(recommendedMedia = emptyFlow()) == SeriesDetailListState(seriesDetail = seriesDetail, seasonDetail = season, images = ImagesContainer(), credits = CreditsContainer(), videos = VideoContainer()))
            job.cancel()
        }
}
