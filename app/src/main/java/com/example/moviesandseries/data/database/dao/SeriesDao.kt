package com.example.moviesandseries.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesandseries.data.database.db.series.DbSeries
import kotlinx.coroutines.flow.Flow

@Dao
interface SeriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbSeries)

    @Query("SELECT * FROM series ORDER BY popularity DESC")
    fun getAllItemsByPopularity(): Flow<List<DbSeries>>

    @Query("SELECT * FROM series ORDER BY voteAverage DESC")
    fun getAllItemsByVoteAverage(): Flow<List<DbSeries>>

    @Query("SELECT * FROM series ORDER BY firstAirDate DESC")
    fun getAllItemsByFirstAirDate(): Flow<List<DbSeries>>

    @Query("SELECT * FROM series WHERE id = :seriesId")
    fun getItem(seriesId: Int): Flow<DbSeries?>

    @Query("SELECT * FROM series WHERE isFavorite = 1")
    fun getAllFavoriteItems(): Flow<List<DbSeries>>

    @Query("UPDATE series SET isFavorite = :isFavorite WHERE id = :seriesId")
    suspend fun updateFavorite(seriesId: Int, isFavorite: Boolean)

    @Query("SELECT * FROM series WHERE name LIKE '%' || :query || '%'")
    fun getItemsByQuery(query: String): Flow<List<DbSeries>>

    @Delete
    suspend fun delete(item: DbSeries)
}
