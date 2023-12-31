package com.example.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviesandseries.data.database.MovieAndSeriesApplicationDb
import com.example.moviesandseries.data.database.dao.MovieDao
import com.example.moviesandseries.data.database.db.asDbObject
import com.example.moviesandseries.data.database.db.asDomainObject
import com.example.moviesandseries.domain.Collection
import com.example.moviesandseries.domain.movie.Movie
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {
    private lateinit var movieDao: MovieDao
    private lateinit var movieDatabase: MovieAndSeriesApplicationDb

    private var movie1 = Movie(
        adult = true,
        backdropPath = "/backdrop.jpg",
        belongsToCollection = Collection(id = 201, name = "Collection A", posterPath = "/poster.jpg"),
        budget = 1000000,
        genres = listOf(),
        homepage = "http://example.com",
        id = 1,
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
        isFavorite = true,
    )

    private var movie2 = Movie(
        adult = true,
        backdropPath = "/backdrop.jpg",
        belongsToCollection = Collection(id = 201, name = "Collection A", posterPath = "/poster.jpg"),
        budget = 1000000,
        genres = listOf(),
        homepage = "http://example.com",
        id = 2,
        imdbId = "tt123456",
        originalLanguage = "en",
        originalTitle = "Original Title",
        overview = "Movie overview",
        popularity = 1.0,
        posterPath = "/poster.jpg",
        productionCompanies = listOf(),
        productionCountries = listOf(),
        releaseDate = "2023-01-01",
        revenue = 5000000,
        runtime = 120,
        spokenLanguages = listOf(),
        status = "Released",
        tagline = "Tagline",
        title = "Movie Title2",
        video = true,
        voteAverage = 8.0,
        voteCount = 200,
        isFavorite = true,
    )

    suspend fun insertMovie(movie: Movie) {
        movieDao.insert(movie.asDbObject())
    }

    suspend fun deleteMovie(movie: Movie) {
        movieDao.delete(movie.asDbObject())
    }

    suspend fun add2Movies() {
        insertMovie(movie1)
        insertMovie(movie2)
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        movieDatabase = Room.inMemoryDatabaseBuilder(context, MovieAndSeriesApplicationDb::class.java)
            .allowMainThreadQueries()
            .build()
        movieDao = movieDatabase.movieDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        movieDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInert_insertMovieIntoDB() = runBlocking {
        insertMovie(movie1)
        val allItems = movieDao.getAllItemsByPopularity().first()
        assertEquals(allItems[0].asDomainObject().id, movie1.id)
        assertEquals(allItems[0].asDomainObject().title, movie1.title)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllMovies_returnsAllMoviesFromDB() = runBlocking {
        add2Movies()
        val allItems = movieDao.getAllItemsByPopularity().first()
        assertEquals(allItems[0].asDomainObject().id, movie1.id)
        assertEquals(allItems[1].asDomainObject().id, movie2.id)
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteOneMovie_returnTotalSizeOne() = runBlocking {
        add2Movies()
        deleteMovie(movie1)
        val allItems = movieDao.getAllItemsByPopularity().first()
        assertEquals(allItems.size, 1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetOneSpecificMovie_returnsOneMovieFromDB() = runBlocking {
        add2Movies()
        val movie = movieDao.getItem(movie1.id).first()
        assertEquals(movie?.asDomainObject()?.id, movie1.id)
    }
    @Test
    @Throws(Exception::class)
    fun daoUpdateFavorite_updatesFavoriteStatus() = runBlocking {
        insertMovie(movie1)
        movieDao.updateFavorite(movie1.id, false)
        val updatedMovie = movieDao.getItem(movie1.id).first()
        assertEquals(updatedMovie?.asDomainObject()?.isFavorite, false)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllFavoriteItems_returnsAllFavoriteMovies() = runBlocking {
        add2Movies()
        val allFavoriteItems = movieDao.getAllFavoriteItems().first()
        assertEquals(allFavoriteItems.size, 2)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetItemsByQuery_returnsMatchingMovies() = runBlocking {
        add2Movies()
        val query = "Movie Title"
        val itemsByQuery = movieDao.getItemsByQuery(query).first()
        assertEquals(itemsByQuery.size, 2)
        assertEquals(itemsByQuery[0].asDomainObject().title, movie1.title)
    }
    @Test
    @Throws(Exception::class)
    fun daoGetAllItemsByVoteAverage_returnsItemsOrderedByVoteAverage() = runBlocking {
        add2Movies()
        val allItemsByVoteAverage = movieDao.getAllItemsByVoteAverage().first()
        assertEquals(allItemsByVoteAverage.size, 2)
        assertEquals(allItemsByVoteAverage[0].asDomainObject().id, movie1.id)
        assertEquals(allItemsByVoteAverage[1].asDomainObject().id, movie2.id)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllItemsByReleaseDate_returnsItemsOrderedByReleaseDate() = runBlocking {
        add2Movies()
        val allItemsByReleaseDate = movieDao.getAllItemsByReleaseDate().first()
        assertEquals(allItemsByReleaseDate.size, 2)
        assertEquals(allItemsByReleaseDate[0].asDomainObject().id, movie1.id)
        assertEquals(allItemsByReleaseDate[1].asDomainObject().id, movie2.id)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllItemsByPopularity_returnsItemsOrderedByPopularity() = runBlocking {
        add2Movies()
        val allItemsByPopularity = movieDao.getAllItemsByPopularity().first()
        assertEquals(allItemsByPopularity.size, 2)
        assertEquals(allItemsByPopularity[0].asDomainObject().id, movie1.id)
        assertEquals(allItemsByPopularity[1].asDomainObject().id, movie2.id)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetItemsByQuery_returnsEmptyListForNonMatchingQuery() = runBlocking {
        add2Movies()
        val query = "Nonexistent Movie"
        val itemsByQuery = movieDao.getItemsByQuery(query).first()
        assertTrue(itemsByQuery.isEmpty())
    }
}
