package com.example.moviesandseries.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesandseries.data.database.db.DbMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbMovie)

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun getAllItemsByPopularity(): Flow<List<DbMovie>>

    @Query("SELECT * FROM movies ORDER BY voteAverage DESC")
    fun getAllItemsByVoteAverage(): Flow<List<DbMovie>>

    @Query("SELECT * FROM movies ORDER BY releaseDate DESC")
    fun getAllItemsByReleaseDate(): Flow<List<DbMovie>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getItem(movieId: Int): Flow<DbMovie>

    @Query("SELECT * FROM movies WHERE isFavorite = 1")
    fun getAllFavoriteItems(): Flow<List<DbMovie>>

    @Query("UPDATE movies SET isFavorite = :isFavorite WHERE id = :movieId")
    suspend fun updateFavorite(movieId: Int, isFavorite: Boolean)

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%'")
    fun getItemsByQuery(query: String): Flow<List<DbMovie>>


}