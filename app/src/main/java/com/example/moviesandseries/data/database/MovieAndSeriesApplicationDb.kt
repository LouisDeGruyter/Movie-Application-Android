package com.example.moviesandseries.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviesandseries.data.database.converters.GenreConverter
import com.example.moviesandseries.data.database.converters.IntConverter
import com.example.moviesandseries.data.database.converters.ProductionCompanyConverter
import com.example.moviesandseries.data.database.converters.ProductionCountryConverter
import com.example.moviesandseries.data.database.converters.SpokenLanguageConverter
import com.example.moviesandseries.data.database.converters.StringConverter
import com.example.moviesandseries.data.database.converters.movie.CollectionConverter
import com.example.moviesandseries.data.database.converters.series.CreatedByConverter
import com.example.moviesandseries.data.database.converters.series.CreditConverter
import com.example.moviesandseries.data.database.converters.series.EpisodeConverter
import com.example.moviesandseries.data.database.converters.series.NetworkConverter
import com.example.moviesandseries.data.database.converters.series.SeasonConverter
import com.example.moviesandseries.data.database.dao.MovieDao
import com.example.moviesandseries.data.database.dao.SeriesDao
import com.example.moviesandseries.data.database.db.DbGenre
import com.example.moviesandseries.data.database.db.DbProductionCompany
import com.example.moviesandseries.data.database.db.DbProductionCountry
import com.example.moviesandseries.data.database.db.DbSpokenLanguage
import com.example.moviesandseries.data.database.db.movies.DbCollection
import com.example.moviesandseries.data.database.db.movies.DbMovie
import com.example.moviesandseries.data.database.db.series.DbCreatedBy
import com.example.moviesandseries.data.database.db.series.DbCredit
import com.example.moviesandseries.data.database.db.series.DbEpisode
import com.example.moviesandseries.data.database.db.series.DbNetwork
import com.example.moviesandseries.data.database.db.series.DbSeason
import com.example.moviesandseries.data.database.db.series.DbSeries

/**
 * Represents the Room database for the Movie and Series application, containing tables for various entities.
 *
 * @property movieDao Provides access to operations related to movies in the database.
 * @property seriesDao Provides access to operations related to series in the database.
 */
@Database(
    entities = [DbCollection::class, DbGenre::class, DbProductionCompany::class, DbProductionCountry::class, DbSpokenLanguage::class, DbMovie::class, DbCreatedBy::class, DbCredit::class, DbEpisode::class, DbNetwork::class, DbSeason::class, DbSeries::class],
    version = 3
)
@TypeConverters(
    CollectionConverter::class, GenreConverter::class, ProductionCompanyConverter::class,
    ProductionCountryConverter::class, SpokenLanguageConverter::class,
    CreatedByConverter::class, EpisodeConverter::class,
    NetworkConverter::class, SeasonConverter::class, IntConverter::class, StringConverter::class, CreditConverter::class
)
abstract class MovieAndSeriesApplicationDb : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun seriesDao(): SeriesDao

    companion object {
        @Volatile
        private var Instance: MovieAndSeriesApplicationDb? = null

        /**
         * Gets an instance of the Room database.
         *
         * @param context The application context.
         * @return The Room database instance.
         */
        fun getDatabase(context: Context): MovieAndSeriesApplicationDb {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    MovieAndSeriesApplicationDb::class.java,
                    "movie_and_series_database",
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}
