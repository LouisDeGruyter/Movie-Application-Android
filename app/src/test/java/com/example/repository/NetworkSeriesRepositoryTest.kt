package com.example.repository

import com.example.moviesandseries.data.database.dao.SeriesDao
import com.example.moviesandseries.data.database.db.series.asDbObject
import com.example.moviesandseries.data.database.db.series.asDomainObject
import com.example.moviesandseries.model.credits.CreditApi
import com.example.moviesandseries.model.credits.CreditsContainerApi
import com.example.moviesandseries.model.credits.asDomainObject
import com.example.moviesandseries.model.images.ImagesContainerApi
import com.example.moviesandseries.model.images.asDomainObject
import com.example.moviesandseries.model.recommendations.RecommendationContainerApi
import com.example.moviesandseries.model.recommendations.asDomainObject
import com.example.moviesandseries.model.reviews.ReviewContainerApi
import com.example.moviesandseries.model.reviews.asDomainObject
import com.example.moviesandseries.model.series.SeriesContainerApi
import com.example.moviesandseries.model.series.SeriesDetailApi
import com.example.moviesandseries.model.series.SeriesIndexApi
import com.example.moviesandseries.model.series.asDomainObject
import com.example.moviesandseries.model.series.episode.EpisodeApi
import com.example.moviesandseries.model.videos.VideoContainerApi
import com.example.moviesandseries.model.videos.asDomainObject
import com.example.moviesandseries.network.SeriesApiService
import com.example.moviesandseries.repository.NetworkSeriesRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class NetworkSeriesRepositoryTest {

    @Mock
    private lateinit var seriesApiService: SeriesApiService

    @Mock
    private lateinit var seriesDao: SeriesDao

    @InjectMocks
    private lateinit var networkSeriesRepository: NetworkSeriesRepository

    private val seriesIndexApiList = listOf(
        SeriesIndexApi(
            backdropPath = "/backdrop1.jpg",
            firstAirDate = "2022-01-01",
            genreIds = listOf(1, 2, 3),
            id = 1,
            name = "Series 1",
            originCountry = listOf("US"),
            originalLanguage = "en",
            originalName = "Original Series 1",
            overview = "Overview 1",
            popularity = 8.5,
            posterPath = "/poster1.jpg",
            voteAverage = 7.5,
            voteCount = 100,
        ),
        SeriesIndexApi(
            backdropPath = "/backdrop2.jpg",
            firstAirDate = "2022-02-15",
            genreIds = listOf(4, 5),
            id = 2,
            name = "Series 2",
            originCountry = listOf("UK"),
            originalLanguage = "es",
            originalName = "Original Series 2",
            overview = "Overview 2",
            popularity = 7.0,
            posterPath = "/poster2.jpg",
            voteAverage = 6.5,
            voteCount = 80,
        ),
    )
    private val seriesContainerApi = SeriesContainerApi(
        page = 1,
        results = seriesIndexApiList,
        totalPages = 1,
        totalResults = 2,
    )

    private val seriesDetailApi = SeriesDetailApi(
        backdropPath = "/backdrop_path.jpg",
        createdBy = listOf(),
        episodeRunTime = listOf(30),
        firstAirDate = "2023-01-01",
        genres = listOf(),
        homepage = "http://example.com",
        id = 123,
        inProduction = true,
        languages = listOf("en"),
        lastAirDate = "2023-12-31",
        name = "Series Title",
        networks = listOf(),
        numberOfEpisodes = 10,
        numberOfSeasons = 2,
        originCountry = listOf("US"),
        originalLanguage = "en",
        originalName = "Original Series Title",
        overview = "Series overview",
        popularity = 9.5,
        posterPath = "/poster.jpg",
        productionCompanies = listOf(),
        seasons = listOf(),
        status = "Returning Series",
        tagline = "Tagline",
        type = "Scripted",
        voteAverage = 8.0,
        voteCount = 200,
        lastEpisodeToAir = EpisodeApi(
            airDate = "2023-12-31",
            episodeNumber = 1,
            id = 1,
            name = "Episode 1",
            overview = "Episode overview",
            productionCode = "123",
            seasonNumber = 1,
            stillPath = "/still.jpg",
            voteAverage = 8.0,
            voteCount = 200,
            runtime = 30,
            showId = 123,
        ),
        nextEpisodeToAir = EpisodeApi(
            airDate = "2023-12-31",
            episodeNumber = 2,
            id = 2,
            name = "Episode 2",
            overview = "Episode overview",
            productionCode = "123",
            seasonNumber = 1,
            stillPath = "/still.jpg",
            voteAverage = 8.0,
            voteCount = 200,
            runtime = 30,
            showId = 123,
        ),
        adult = false,
        productionCountries = listOf(),
        spokenLanguages = listOf(),

    )

    private val creditsContainerApi = CreditsContainerApi(
        id = 1,
        cast = listOf(
            CreditApi(
                adult = false,
                castId = 123,
                character = "John Doe",
                creditId = "abc123",
                gender = 1,
                id = 456,
                knownForDepartment = "Acting",
                name = "John Doe",
                order = 1,
                originalName = "John",
                popularity = 7.5,
                profilePath = "/profile.jpg",
            ),
        ),
        crew = listOf(
            CreditApi(
                adult = false,
                castId = 123,
                character = "",
                creditId = "15",
                gender = 1,
                id = 123,
                knownForDepartment = "Screening",
                name = "Eva Doe",
                order = 1,
                originalName = "Eva",
                popularity = 7.5,
                profilePath = "/profile.jpg",
            ),
        ),
    )

    private val recommendationContainerApi =
        RecommendationContainerApi(page = 1, results = listOf(), totalPages = 1, totalResults = 2)

    private val reviewContainerApi = ReviewContainerApi(
        id = 1,
        page = 1,
        results = listOf(),
        totalPages = 1,
        totalResults = 2,
    )

    private val videoContainerApi = VideoContainerApi(
        id = 1,
        results = listOf(),
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test getSeriesContainer`() = runBlocking {
        `when`(seriesApiService.getSeriesContainer(1)).thenReturn(seriesContainerApi)
        val result = networkSeriesRepository.getSeriesContainer(1)

        assertEquals(seriesContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getSeries`() = runBlocking {
        `when`(seriesApiService.getSeriesContainer(1)).thenReturn(seriesContainerApi)
        val result = networkSeriesRepository.getSeries(1)

        assertEquals(seriesIndexApiList.map { it.asDomainObject() }, result)
    }

    @Test
    fun `test getSeriesDetail`() = runBlocking {
        val seriesDetailDb = seriesDetailApi.asDomainObject().asDbObject()

        `when`(seriesDao.getItem(seriesDetailDb.id)).thenReturn(flowOf(seriesDetailDb))
        val result = networkSeriesRepository.getSeriesDetail(seriesDetailDb.id).first()

        assertEquals(seriesDetailDb.asDomainObject(), result)
    }

    @Test
    fun `test getSeriesCredits`() = runBlocking {
        val seriesId = 123

        `when`(seriesApiService.getSeriesCredits(seriesId)).thenReturn(creditsContainerApi)
        val result = networkSeriesRepository.getSeriesCredits(seriesId)

        assertEquals(creditsContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getSeriesImages`() = runBlocking {
        val seriesId = 123
        val imagesContainerApi =
            ImagesContainerApi(backdrops = listOf(), posters = listOf(), id = 123, logos = listOf())

        `when`(seriesApiService.getSeriesImages(seriesId)).thenReturn(imagesContainerApi)
        val result = networkSeriesRepository.getSeriesImages(seriesId)

        assertEquals(imagesContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getSimilarSeries`() = runBlocking {
        val seriesId = 123
        val page = 1

        `when`(seriesApiService.getSimilarSeries(seriesId, page)).thenReturn(seriesContainerApi)
        val result = networkSeriesRepository.getSimilarSeries(seriesId, page)

        assertEquals(seriesContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getRecommendedSeries`() = runBlocking {
        val seriesId = 123
        val page = 1

        `when`(seriesApiService.getRecommendedSeries(seriesId, page)).thenReturn(
            recommendationContainerApi,
        )
        val result = networkSeriesRepository.getRecommendedSeries(seriesId, page)

        assertEquals(recommendationContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getSeriesReviews`() = runBlocking {
        val seriesId = 123
        val page = 1

        `when`(seriesApiService.getSeriesReviews(seriesId, page)).thenReturn(reviewContainerApi)
        val result = networkSeriesRepository.getSeriesReviews(seriesId, page)

        assertEquals(reviewContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getSeriesPopular`() = runBlocking {
        val page = 1

        `when`(seriesApiService.getSeriesPopular(page)).thenReturn(seriesContainerApi)
        val result = networkSeriesRepository.getSeriesPopular(page)

        assertEquals(seriesContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getSeriesTopRated`() = runBlocking {
        val page = 1

        `when`(seriesApiService.getSeriesTopRated(page)).thenReturn(seriesContainerApi)
        val result = networkSeriesRepository.getSeriesTopRated(page)

        assertEquals(seriesContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getSeriesOnTheAir`() = runBlocking {
        val page = 1

        `when`(seriesApiService.getSeriesOnTheAir(page)).thenReturn(seriesContainerApi)
        val result = networkSeriesRepository.getSeriesOnTheAir(page)

        assertEquals(seriesContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getSeriesAiringToday`() = runBlocking {
        val page = 1

        `when`(seriesApiService.getSeriesAiringToday(page)).thenReturn(seriesContainerApi)
        val result = networkSeriesRepository.getSeriesAiringToday(page)

        assertEquals(seriesContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getSeriesVideos`() = runBlocking {
        val seriesId = 123
        val videoContainerApi = VideoContainerApi(
            id = 1,
            results = listOf(),
        )

        `when`(seriesApiService.getSeriesVideos(seriesId)).thenReturn(videoContainerApi)
        val result = networkSeriesRepository.getSeriesVideos(seriesId)

        assertEquals(videoContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test insertSeries`() = runBlocking {
        val series = seriesDetailApi.asDomainObject()
        `when`(seriesDao.getItem(series.id)).thenReturn(flowOf(null))
        networkSeriesRepository.insertSeries(series)
        val seriesDetailDb = series.asDbObject()
        verify(seriesDao, times(1)).insert(seriesDetailDb)
    }

    @Test
    fun `test getFavoriteSeries`() = runBlocking {
        val series = seriesIndexApiList.map { it.asDomainObject().asDbObject() }
        `when`(seriesDao.getAllFavoriteItems()).thenReturn(flowOf(series))
        val result = networkSeriesRepository.getFavoriteSeries().first()
        assertEquals(series.asDomainObject(), result)
    }
}
