package com.example.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesandseries.domain.Dates
import com.example.moviesandseries.domain.Genre
import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.domain.movie.Movie
import com.example.moviesandseries.domain.movie.MovieContainer
import com.example.moviesandseries.domain.movie.MovieContainerWithDates
import com.example.moviesandseries.paging.movies.MoviesInTheaterPagingSource
import com.example.moviesandseries.repository.MovieRepository
import com.example.moviesandseries.repository.SeriesRepository
import com.example.moviesandseries.screens.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel
    private var movieRepository: MovieRepository = mockk()
    private var seriesRepository: SeriesRepository = mockk()

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

    private fun getMockMovies(): List<Movie> = listOf(
        Movie(
            id = 1,
            title = "The Shawshank Redemption",
            overview = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
            releaseDate = "1994-09-23",
            voteAverage = 9.3,
            voteCount = 18927,
            posterPath = "/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg",
            backdropPath = "/j9XKiZrVeViAixVRzCta7h1VU9W.jpg",
            genres = listOf(Genre(id = 18, name = "Drama"), Genre(id = 80, name = "Crime")),
            originalLanguage = "en",
            popularity = 53.37,
            isFavorite = true,
        ),
        Movie(
            id = 2,
            title = "Breaking Bad: The Movie",
            overview = "A high school chemistry teacher turned methamphetamine manufacturer partners with a former student.",
            releaseDate = "2022-05-01",
            voteAverage = 9.5,
            voteCount = 15388,
            posterPath = "/1yeVJox3rjo2jBKrrihIMj7uoS9.jpg",
            backdropPath = "/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg",
            genres = listOf(Genre(id = 18, name = "Drama")),
            originalLanguage = "en",
            popularity = 70.24,
        ),
        Movie(
            id = 3,
            title = "The Dark Knight",
            overview = "A vigilante known as Batman sets out to dismantle the remaining criminal organizations that plague Gotham City.",
            releaseDate = "2008-07-18",
            voteAverage = 9.2,
            voteCount = 26789,
            posterPath = "/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
            backdropPath = "/hkBaDkMWbLaf8B1lsWsKX7VDfu5.jpg",
            genres = listOf(
                Genre(id = 28, name = "Action"),
                Genre(id = 80, name = "Crime"),
                Genre(id = 18, name = "Drama"),
                Genre(id = 53, name = "Thriller"),
            ),
            originalLanguage = "en",
            popularity = 59.99,
            isFavorite = true,
        ),
        Movie(
            id = 4,
            title = "The Godfather",
            overview = "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family.",
            releaseDate = "1972-03-14",
            voteAverage = 9.2,
            voteCount = 15388,
            posterPath = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
            backdropPath = "/ejdD20cdHNFAYAN2DlqPToXKyzx.jpg",
            genres = listOf(Genre(id = 18, name = "Drama"), Genre(id = 80, name = "Crime")),
            originalLanguage = "en",
            popularity = 59.99,
        ),
        Movie(
            id = 5,
            title = "The Godfather: Part II",
            overview = "In the continuing saga of the Corleone crime family, a young Vito Corleone grows up in Sicily and in 1910s New York.",
            releaseDate = "1974-12-20",
            voteAverage = 9.2,
            voteCount = 15388,
            posterPath = "/hek3koDUyRQk7FIhPXsa6mT2Zc3.jpg",
            backdropPath = "/poec6RqOKY9iSiIUmfyfPfiLtvB.jpg",
            genres = listOf(Genre(id = 18, name = "Drama"), Genre(id = 80, name = "Crime")),
            originalLanguage = "en",
            popularity = 59.99,
        ),
        Movie(
            id = 6,
            title = "The Lord of the Rings: The Return of the King",
            overview = "Aragorn is revealed as the heir to the ancient kings as he, Gandalf and the other members of the broken fellowship struggle to save Gondor.",
            releaseDate = "2003-12-01",
            voteAverage = 9.2,
            voteCount = 15388,
            posterPath = "/rCzpDGLbOoPwLjy3OAm5NUPOTrC.jpg",
            backdropPath = "/8BPZO0Bf8TeAy8znF43z8soK3ys.jpg",
            genres = listOf(Genre(id = 12, name = "Adventure"), Genre(id = 14, name = "Fantasy")),
            originalLanguage = "en",
            popularity = 59.99,
        ),
        Movie(
            id = 7,
            title = "The Lord of the Rings: The Fellowship of the Ring",
            overview = "Frodo and Sam are trekking to Mordor to destroy the One Ring of Power while Gimli, Legolas and Aragorn search for the orc-captured Merry and Pippin.",
            releaseDate = "2001-12-18",
            voteAverage = 9.2,
            voteCount = 15388,
            posterPath = "/6oom5QYQ2yQTMJIbnvbkBL9cHo6.jpg",
            backdropPath = "/pIUvQ9Ed35wlWhY2oU6OmwEsmzG.jpg",
            genres = listOf(Genre(id = 12, name = "Adventure"), Genre(id = 14, name = "Fantasy")),
            originalLanguage = "en",
            popularity = 59.99,
        ),

    )

    fun getMovieContainer(): MovieContainer {
        val movies = getMockMovies()
        return MovieContainer(
            page = 1,
            results = movies,
            totalPages = 1,
            totalResults = 1,
        )
    }
    fun getMovieContainerWithDates(): MovieContainerWithDates {
        val movies = getMockMovies()
        return MovieContainerWithDates(
            page = 1,
            results = movies,
            totalPages = 1,
            totalResults = 1,
            dates = Dates(
                maximum = "2021-09-30",
                minimum = "2021-08-13",
            ),
        )
    }

    /**@Test
    fun `test moviesInTheaterPager content`() = runBlocking {
        viewModel = HomeViewModel(movieRepository, seriesRepository)
        // Mock the expected data from your repository
        val mockMovies = getMockMovies()
        coEvery { movieRepository.getMoviesInTheaters(1) } returns getMovieContainerWithDates()

        // Create a Pager with your PagingSource
        val pager: Flow<PagingData<MediaIndex>> = Pager(PagingConfig(pageSize = 20)) {
            MoviesInTheaterPagingSource(movieRepository)
        }.flow.cachedIn(viewModel.viewModelScope)

        assert(pager == viewModel.moviesInTheaterPager)
    }**/
}
