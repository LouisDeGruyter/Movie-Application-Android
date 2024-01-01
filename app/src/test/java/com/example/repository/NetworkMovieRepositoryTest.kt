package com.example.repository

import com.example.moviesandseries.data.database.dao.MovieDao
import com.example.moviesandseries.data.database.db.asDbObject
import com.example.moviesandseries.data.database.db.asDomainObject
import com.example.moviesandseries.model.DatesApi
import com.example.moviesandseries.model.GenreApi
import com.example.moviesandseries.model.collection.CollectionIndexApi
import com.example.moviesandseries.model.credits.CreditApi
import com.example.moviesandseries.model.credits.CreditsContainerApi
import com.example.moviesandseries.model.credits.asDomainObject
import com.example.moviesandseries.model.images.ImagesContainerApi
import com.example.moviesandseries.model.images.asDomainObject
import com.example.moviesandseries.model.movie.MovieContainerApi
import com.example.moviesandseries.model.movie.MovieContainerWithDatesApi
import com.example.moviesandseries.model.movie.MovieDetailApi
import com.example.moviesandseries.model.movie.MovieIndexApi
import com.example.moviesandseries.model.movie.asDomainObject
import com.example.moviesandseries.model.recommendations.RecommendationContainerApi
import com.example.moviesandseries.model.recommendations.asDomainObject
import com.example.moviesandseries.model.reviews.ReviewContainerApi
import com.example.moviesandseries.model.reviews.asDomainObject
import com.example.moviesandseries.model.videos.VideoContainerApi
import com.example.moviesandseries.model.videos.asDomainObject
import com.example.moviesandseries.network.MovieApiService
import com.example.moviesandseries.repository.NetworkMovieRepository
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
class NetworkMovieRepositoryTest {

    @Mock
    private lateinit var movieApiService: MovieApiService

    @Mock
    private lateinit var movieDao: MovieDao

    @InjectMocks
    private lateinit var networkMovieRepository: NetworkMovieRepository

    private val movieIndexApiList = listOf(
        MovieIndexApi(
            adult = false,
            backdropPath = "/backdrop1.jpg",
            genreIds = listOf(1, 2, 3),
            id = 1,
            originalLanguage = "en",
            originalTitle = "Original Title 1",
            overview = "Overview 1",
            popularity = 8.5,
            posterPath = "/poster1.jpg",
            releaseDate = "2022-01-01",
            title = "Movie Title 1",
            video = false,
            voteAverage = 7.5,
            voteCount = 100,
        ),
        MovieIndexApi(
            adult = true,
            backdropPath = "/backdrop2.jpg",
            genreIds = listOf(4, 5),
            id = 2,
            originalLanguage = "es",
            originalTitle = "Original Title 2",
            overview = "Overview 2",
            popularity = 7.0,
            posterPath = "/poster2.jpg",
            releaseDate = "2022-02-15",
            title = "Movie Title 2",
            video = true,
            voteAverage = 6.5,
            voteCount = 80,
        ),
    )
    private val movieContainerApi = MovieContainerApi(
        page = 1,
        results = movieIndexApiList,
        totalPages = 1,
        totalResults = 2,
    )

    private val movieDetailApi = MovieDetailApi(
        adult = false,
        backdropPath = "/backdrop_path.jpg",
        belongsToCollection = CollectionIndexApi(backdropPath = "", id = 1, name = "", posterPath = ""),
        budget = 1000000,
        genres = listOf(
            GenreApi(id = 1, name = "Action"),
            GenreApi(id = 2, name = "Drama"),
        ),
        homepage = "http://example.com",
        id = 123,
        imdbId = "tt123456",
        originalLanguage = "en",
        originalTitle = "Original Title",
        overview = "Movie overview",
        popularity = 9.5,
        posterPath = "/poster.jpg",
        productionCompanies = listOf(),
        productionCountries = listOf(),
        releaseDate = "2023-01-01",
        revenue = 5000000,
        runtime = 120,
        spokenLanguages = listOf(),
        status = "Released",
        tagline = "Tagline",
        title = "Movie Title",
        video = true,
        voteAverage = 8.0,
        voteCount = 200,
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

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test getMoviesContainer`() = runBlocking {
        `when`(movieApiService.getMoviesContainer(1)).thenReturn(movieContainerApi)
        val result = networkMovieRepository.getMoviesContainer(1)

        assertEquals(movieContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getMovies`() = runBlocking {
        `when`(movieApiService.getMoviesContainer(1)).thenReturn(movieContainerApi)
        val result = networkMovieRepository.getMovies(1)

        assertEquals(movieIndexApiList.map { it.asDomainObject() }, result)
    }

    @Test
    fun `test getMovieDetail`() = runBlocking {
        val movieDetailDb = movieDetailApi.asDomainObject().asDbObject()

        `when`(movieDao.getItem(movieDetailDb.id)).thenReturn(flowOf(movieDetailDb))
        val result = networkMovieRepository.getMovieDetail(movieDetailDb.id).first()

        assertEquals(movieDetailDb.asDomainObject(), result)
    }

    @Test
    fun `test getMovieCredits`() = runBlocking {
        val movieId = 123

        `when`(movieApiService.getMovieCredits(movieId)).thenReturn(creditsContainerApi)
        val result = networkMovieRepository.getMovieCredits(movieId)

        assertEquals(creditsContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getMovieImages`() = runBlocking {
        val movieId = 123
        val imagesContainerApi = ImagesContainerApi(backdrops = listOf(), posters = listOf(), id = 123, logos = listOf())

        `when`(movieApiService.getMovieImages(movieId)).thenReturn(imagesContainerApi)
        val result = networkMovieRepository.getMovieImages(movieId)

        assertEquals(imagesContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getSimilarMovies`() = runBlocking {
        val movieId = 123
        val page = 1

        `when`(movieApiService.getSimilarMovies(movieId, page)).thenReturn(movieContainerApi)
        val result = networkMovieRepository.getSimilarMovies(movieId, page)

        assertEquals(movieContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getRecommendedMovies`() = runBlocking {
        val movieId = 123
        val page = 1
        val recommendationContainerApi = RecommendationContainerApi(page = 1, results = listOf(), totalPages = 1, totalResults = 2)

        `when`(movieApiService.getRecommendedMovies(movieId, page)).thenReturn(recommendationContainerApi)
        val result = networkMovieRepository.getRecommendedMovies(movieId, page)

        assertEquals(recommendationContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getMovieReviews`() = runBlocking {
        val movieId = 123
        val page = 1
        val reviewContainerApi = ReviewContainerApi(
            id = 1,
            page = 1,
            results = listOf(),
            totalPages = 1,
            totalResults = 2,
        )

        `when`(movieApiService.getMovieReviews(movieId, page)).thenReturn(reviewContainerApi)
        val result = networkMovieRepository.getMovieReviews(movieId, page)

        assertEquals(reviewContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getMoviesInTheaters`() = runBlocking {
        val page = 1
        val moviesInTheatersContainerApi = MovieContainerWithDatesApi(
            dates = DatesApi(
                maximum = "2021-10-01",
                minimum = "2021-09-01",
            ),
            page = 1,
            results = movieIndexApiList,
            totalPages = 1,
            totalResults = 2,
        )

        `when`(movieApiService.getMoviesInTheaters(page)).thenReturn(moviesInTheatersContainerApi)
        val result = networkMovieRepository.getMoviesInTheaters(page)

        assertEquals(moviesInTheatersContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getMoviesPopular`() = runBlocking {
        val page = 1
        val moviesPopularContainerApi = MovieContainerApi(
            page = 1,
            results = movieIndexApiList,
            totalPages = 1,
            totalResults = 2,
        )

        `when`(movieApiService.getMoviesPopular(page)).thenReturn(moviesPopularContainerApi)
        val result = networkMovieRepository.getMoviesPopular(page)

        assertEquals(moviesPopularContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getMoviesTopRated`() = runBlocking {
        val page = 1
        val moviesTopRatedContainerApi = MovieContainerApi(
            page = 1,
            results = movieIndexApiList,
            totalPages = 1,
            totalResults = 2,
        )

        `when`(movieApiService.getMoviesTopRated(page)).thenReturn(moviesTopRatedContainerApi)
        val result = networkMovieRepository.getMoviesTopRated(page)

        assertEquals(moviesTopRatedContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getMoviesUpcoming`() = runBlocking {
        val page = 1
        val moviesUpcomingContainerApi = MovieContainerWithDatesApi(
            dates = DatesApi(
                maximum = "2021-10-01",
                minimum = "2021-09-01",
            ),
            page = 1,
            results = movieIndexApiList,
            totalPages = 1,
            totalResults = 2,
        )

        `when`(movieApiService.getMoviesUpcoming(page)).thenReturn(moviesUpcomingContainerApi)
        val result = networkMovieRepository.getMoviesUpcoming(page)

        assertEquals(moviesUpcomingContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test getMovieVideos`() = runBlocking {
        val movieId = 123
        val videoContainerApi = VideoContainerApi(
            id = 1,
            results = listOf(),
        )

        `when`(movieApiService.getMovieVideos(movieId)).thenReturn(videoContainerApi)
        val result = networkMovieRepository.getMovieVideos(movieId)

        assertEquals(videoContainerApi.asDomainObject(), result)
    }

    @Test
    fun `test insertMovie`() = runBlocking {
        val movie = movieDetailApi.asDomainObject()
        `when`(movieDao.getItem(movie.id)).thenReturn(flowOf(null))
        networkMovieRepository.insertMovie(movie)

        val movieDb = movie.asDbObject()
        verify(movieDao, times(1)).insert(movieDb)
    }

    @Test
    fun `test getFavoriteMovies`() = runBlocking {
        val dbMovies = movieIndexApiList.map { it.asDomainObject().asDbObject() }

        `when`(movieDao.getAllFavoriteItems()).thenReturn(flowOf(dbMovies))
        val result = networkMovieRepository.getFavoriteMovies().first()

        assertEquals(dbMovies.asDomainObject(), result)
    }
}
