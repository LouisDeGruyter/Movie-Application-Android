package com.example.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesandseries.domain.Collection
import com.example.moviesandseries.domain.Genre
import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.domain.ProductionCompany
import com.example.moviesandseries.domain.ProductionCountry
import com.example.moviesandseries.domain.RecommendationContainer
import com.example.moviesandseries.domain.SpokenLanguage
import com.example.moviesandseries.domain.credits.CreditsContainer
import com.example.moviesandseries.domain.images.ImagesContainer
import com.example.moviesandseries.domain.movie.Movie
import com.example.moviesandseries.model.videos.VideoContainer
import com.example.moviesandseries.paging.movies.RecommendedMoviesPagingSource
import com.example.moviesandseries.repository.CollectionRepository
import com.example.moviesandseries.repository.MovieRepository
import com.example.moviesandseries.screens.movies.detail.MovieDetailApiState
import com.example.moviesandseries.screens.movies.detail.MovieDetailListState
import com.example.moviesandseries.screens.movies.detail.MovieDetailViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
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
class MovieDetailViewModelTest {
    private lateinit var viewModel: MovieDetailViewModel
    private var movieRepository: MovieRepository = mockk()
    private var collectionRepository: CollectionRepository = mockk()

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
    val collection: Collection = Collection(
        id = 1,
        name = "The Shawshank Redemption Collection",
        overview = "The Shawshank Redemption Collection",
        posterPath = "/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg",
        backdropPath = "/j9XKiZrVeViAixVRzCta7h1VU9W.jpg",
    )

    val movieDetail = Movie(
        adult = false,
        backdropPath = "/backdrop_path.jpg",
        belongsToCollection = collection,
        budget = 1000000,
        genres = listOf(Genre(/* populate Genre properties */)),
        homepage = "http://example.com",
        id = 123,
        imdbId = "tt123456",
        originalLanguage = "en",
        originalTitle = "Original Title",
        overview = "Movie overview",
        popularity = 9.5,
        posterPath = "/poster.jpg",
        productionCompanies = listOf(ProductionCompany(/* populate ProductionCompany properties */)),
        productionCountries = listOf(ProductionCountry(/* populate ProductionCountry properties */)),
        releaseDate = "2023-01-01",
        revenue = 5000000,
        runtime = 120,
        spokenLanguages = listOf(SpokenLanguage(/* populate SpokenLanguage properties */)),
        status = "Released",
        tagline = "Tagline",
        title = "Movie Title",
        video = true,
        voteAverage = 8.0,
        voteCount = 200,
        isFavorite = false,
    )

    @Test
    fun `getMovieDetail should update uiListMovieDetailState and set movieDetailApiState to succes`() =
        testScope.runTest {
            coEvery { movieRepository.getMovieDetail(movieDetail.id) } returns flowOf(movieDetail)
            coEvery { collectionRepository.getCollectionDetail(movieDetail.belongsToCollection.id) } returns collection
            coEvery { movieRepository.refreshMovie(movieId = movieDetail.id) } returns Unit
            coEvery { movieRepository.getMovieImages(movieDetail.id) } returns ImagesContainer()
            coEvery { movieRepository.getMovieCredits(movieDetail.id) } returns CreditsContainer()
            coEvery { movieRepository.getMovieVideos(movieDetail.id) } returns VideoContainer()
            coEvery { movieRepository.getRecommendedMovies(movieId = movieDetail.id, page = 1) } returns RecommendationContainer()

            viewModel = MovieDetailViewModel(movieRepository, collectionRepository)
            viewModel.getMovieDetail(movieDetail.id)
            val collectCompleted = CompletableDeferred<Unit>()
            var uiListMovieDetailState: MovieDetailListState? = null
            val job = launch {
                viewModel.uiListMovieDetailState.collect {
                    uiListMovieDetailState = it
                    collectCompleted.complete(Unit)
                }
            }

            collectCompleted.await()

            advanceUntilIdle()

            assert(viewModel.movieDetailApiState is MovieDetailApiState.Success)
            val shoudlBeMovieDetailListState = MovieDetailListState(
                movieDetail = movieDetail,
                collection = collection,
                images = ImagesContainer(),
                credits = CreditsContainer(),
                videos = VideoContainer(),
            )
            assert(uiListMovieDetailState?.copy(recommendedMedia = emptyFlow())  == shoudlBeMovieDetailListState)
            job.cancel()
        }

    @Test
    fun `updateFavorite should update uiListMovieDetailState`() =
        testScope.runTest {
            coEvery { movieRepository.updateFavorite(movieDetail.id, !movieDetail.isFavorite) } returns Unit
            coEvery { movieRepository.getMovieDetail(movieDetail.id) } returns flowOf(movieDetail)
            coEvery { collectionRepository.getCollectionDetail(movieDetail.belongsToCollection.id) } returns collection
            coEvery { movieRepository.refreshMovie(movieId = movieDetail.id) } returns Unit
            coEvery { movieRepository.getMovieImages(movieDetail.id) } returns ImagesContainer()
            coEvery { movieRepository.getMovieCredits(movieDetail.id) } returns CreditsContainer()
            coEvery { movieRepository.getMovieVideos(movieDetail.id) } returns VideoContainer()
            coEvery { movieRepository.getRecommendedMovies(movieId = movieDetail.id, page = 1) } returns RecommendationContainer()
            viewModel = MovieDetailViewModel(movieRepository, collectionRepository)
            viewModel.getMovieDetail(movieDetail.id)
            viewModel.updateFavorite()
            val collectCompleted = CompletableDeferred<Unit>()
            var uiListMovieDetailState: MovieDetailListState? = null
            val job = launch {
                viewModel.uiListMovieDetailState.collect {
                    uiListMovieDetailState = it
                    collectCompleted.complete(Unit)
                }
            }
            collectCompleted.await()
            advanceUntilIdle()
            assert(uiListMovieDetailState?.movieDetail?.isFavorite == !movieDetail.isFavorite)
            job.cancel()
        }
}
