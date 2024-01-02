package com.example.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviesandseries.data.database.MovieAndSeriesApplicationDb
import com.example.moviesandseries.data.database.dao.SeriesDao
import com.example.moviesandseries.data.database.db.series.asDbObject
import com.example.moviesandseries.data.database.db.series.asDomainObject
import com.example.moviesandseries.domain.Collection
import com.example.moviesandseries.domain.series.Episode
import com.example.moviesandseries.domain.series.Series
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
class SeriesDaoTest {
    private lateinit var seriesDao: SeriesDao
    private lateinit var seriesDatabase: MovieAndSeriesApplicationDb

    private var series1 = Series(
        adult = true,
        backdropPath = "/backdrop.jpg",
        createdBy = listOf(),
        episodeRunTime = listOf(),
        firstAirDate = "2023-01-01",
        genres = listOf(),
        homepage = "http://example.com",
        id = 1,
        inProduction = true,
        languages = listOf(),
        lastAirDate = "2023-12-31",
        lastEpisodeToAir = Episode(),
        name = "Series Title",
        networks = listOf(),
        nextEpisodeToAir = Episode(),
        numberOfEpisodes = 10,
        numberOfSeasons = 2,
        originCountry = listOf(),
        originalLanguage = "en",
        originalName = "Original Series Title",
        overview = "Series overview",
        popularity = 9.5,
        posterPath = "/poster.jpg",
        productionCompanies = listOf(),
        productionCountries = listOf(),
        seasons = listOf(),
        spokenLanguages = listOf(),
        status = "Returning Series",
        tagline = "Tagline",
        type = "Scripted",
        voteAverage = 8.0,
        voteCount = 200,
        isFavorite = true,
    )

    private var series2 = Series(
        adult = true,
        backdropPath = "/backdrop.jpg",
        createdBy = listOf(),
        episodeRunTime = listOf(),
        firstAirDate = "2023-01-01",
        genres = listOf(),
        homepage = "http://example.com",
        id = 2,
        inProduction = true,
        languages = listOf(),
        lastAirDate = "2023-12-31",
        lastEpisodeToAir = Episode(),
        name = "Series Title2",
        networks = listOf(),
        nextEpisodeToAir = Episode(),
        numberOfEpisodes = 12,
        numberOfSeasons = 3,
        originCountry = listOf(),
        originalLanguage = "en",
        originalName = "Original Series Title2",
        overview = "Series overview2",
        popularity = 8.0,
        posterPath = "/poster.jpg",
        productionCompanies = listOf(),
        productionCountries = listOf(),
        seasons = listOf(),
        spokenLanguages = listOf(),
        status = "Returning Series",
        tagline = "Tagline2",
        type = "Scripted",
        voteAverage = 7.5,
        voteCount = 180,
        isFavorite = true,
    )

    suspend fun insertSeries(series: Series) {
        seriesDao.insert(series.asDbObject())
    }

    suspend fun deleteSeries(series: Series) {
        seriesDao.delete(series.asDbObject())
    }

    suspend fun add2Series() {
        insertSeries(series1)
        insertSeries(series2)
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        seriesDatabase = Room.inMemoryDatabaseBuilder(context, MovieAndSeriesApplicationDb::class.java)
            .allowMainThreadQueries()
            .build()
        seriesDao = seriesDatabase.seriesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        seriesDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInert_insertSeriesIntoDB() = runBlocking {
        insertSeries(series1)
        val allItems = seriesDao.getAllItemsByPopularity().first()
        assertEquals(allItems[0].asDomainObject().id, series1.id)
        assertEquals(allItems[0].asDomainObject().name, series1.name)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllSeries_returnsAllSeriesFromDB() = runBlocking {
        add2Series()
        val allItems = seriesDao.getAllItemsByPopularity().first()
        assertEquals(allItems[0].asDomainObject().id, series1.id)
        assertEquals(allItems[1].asDomainObject().id, series2.id)
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteOneSeries_returnTotalSizeOne() = runBlocking {
        add2Series()
        deleteSeries(series1)
        val allItems = seriesDao.getAllItemsByPopularity().first()
        assertEquals(allItems.size, 1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetOneSpecificSeries_returnsOneSeriesFromDB() = runBlocking {
        add2Series()
        val series = seriesDao.getItem(series1.id).first()
        assertEquals(series?.asDomainObject()?.id, series1.id)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateFavorite_updatesFavoriteStatus() = runBlocking {
        insertSeries(series1)
        seriesDao.updateFavorite(series1.id, false)
        val updatedSeries = seriesDao.getItem(series1.id).first()
        assertEquals(updatedSeries?.asDomainObject()?.isFavorite, false)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllFavoriteItems_returnsAllFavoriteSeries() = runBlocking {
        add2Series()
        val allFavoriteItems = seriesDao.getAllFavoriteItems().first()
        assertEquals(allFavoriteItems.size, 2)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetItemsByQuery_returnsMatchingSeries() = runBlocking {
        add2Series()
        val query = "Series Title"
        val itemsByQuery = seriesDao.getItemsByQuery(query).first()
        assertEquals(itemsByQuery.size, 2)
        assertEquals(itemsByQuery[0].asDomainObject().name, series1.name)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllItemsByVoteAverage_returnsItemsOrderedByVoteAverage() = runBlocking {
        add2Series()
        val allItemsByVoteAverage = seriesDao.getAllItemsByVoteAverage().first()
        assertEquals(allItemsByVoteAverage.size, 2)
        assertEquals(allItemsByVoteAverage[0].asDomainObject().id, series1.id)
        assertEquals(allItemsByVoteAverage[1].asDomainObject().id, series2.id)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllItemsByFirstAirDate_returnsItemsOrderedByFirstAirDate() = runBlocking {
        add2Series()
        val allItemsByFirstAirDate = seriesDao.getAllItemsByFirstAirDate().first()
        assertEquals(allItemsByFirstAirDate.size, 2)
        assertEquals(allItemsByFirstAirDate[0].asDomainObject().id, series1.id)
        assertEquals(allItemsByFirstAirDate[1].asDomainObject().id, series2.id)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllItemsByPopularity_returnsItemsOrderedByPopularity() = runBlocking {
        add2Series()
        val allItemsByPopularity = seriesDao.getAllItemsByPopularity().first()
        assertEquals(allItemsByPopularity.size, 2)
        assertEquals(allItemsByPopularity[0].asDomainObject().id, series1.id)
        assertEquals(allItemsByPopularity[1].asDomainObject().id, series2.id)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetItemsByQuery_returnsEmptyListForNonMatchingQuery() = runBlocking {
        add2Series()
        val query = "Nonexistent Series"
        val itemsByQuery = seriesDao.getItemsByQuery(query).first()
        assertTrue(itemsByQuery.isEmpty())
    }
}
