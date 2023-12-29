package com.example.moviesandseries.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviesandseries.data.database.converters.CollectionConverter
import com.example.moviesandseries.data.database.converters.GenreConverter
import com.example.moviesandseries.data.database.converters.MovieConverter
import com.example.moviesandseries.data.database.converters.ProductionCompanyConverter
import com.example.moviesandseries.data.database.converters.ProductionCountryConverter
import com.example.moviesandseries.data.database.converters.SpokenLanguageConverter
import com.example.moviesandseries.data.database.dao.MovieDao
import com.example.moviesandseries.data.database.db.DbCollection
import com.example.moviesandseries.data.database.db.DbGenre
import com.example.moviesandseries.data.database.db.DbMovie
import com.example.moviesandseries.data.database.db.DbProductionCompany
import com.example.moviesandseries.data.database.db.DbProductionCountry
import com.example.moviesandseries.data.database.db.DbSpokenLanguage

@Database(entities = [DbCollection::class, DbGenre::class, DbProductionCompany::class, DbProductionCountry::class, DbSpokenLanguage::class, DbMovie::class], version = 1)
@TypeConverters(CollectionConverter::class, GenreConverter::class, ProductionCompanyConverter::class, ProductionCountryConverter::class, SpokenLanguageConverter::class, MovieConverter::class)
abstract class MovieAndSeriesApplicationDb : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var Instance: MovieAndSeriesApplicationDb? = null

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
