package com.example.moviesandseries.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesandseries.data.database.db.movies.DbMovie
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for movie-related database operations.
 */
@Dao
interface MovieDao {

    /**
     * Inserts or replaces a [DbMovie] item in the database.
     *
     * @param item The [DbMovie] item to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbMovie)

    /**
     * Retrieves all movie items ordered by popularity in descending order.
     *
     * @return A [Flow] emitting a list of [DbMovie] items ordered by popularity.
     */
    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun getAllItemsByPopularity(): Flow<List<DbMovie>>

    /**
     * Retrieves all movie items ordered by vote average in descending order.
     *
     * @return A [Flow] emitting a list of [DbMovie] items ordered by vote average.
     */
    @Query("SELECT * FROM movies ORDER BY voteAverage DESC")
    fun getAllItemsByVoteAverage(): Flow<List<DbMovie>>

    /**
     * Retrieves all movie items ordered by release date in descending order.
     *
     * @return A [Flow] emitting a list of [DbMovie] items ordered by release date.
     */
    @Query("SELECT * FROM movies ORDER BY releaseDate DESC")
    fun getAllItemsByReleaseDate(): Flow<List<DbMovie>>

    /**
     * Retrieves a specific movie item by its ID.
     *
     * @param movieId The ID of the movie to retrieve.
     * @return A [Flow] emitting the [DbMovie] item with the specified ID, or null if not found.
     */
    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getItem(movieId: Int): Flow<DbMovie?>

    /**
     * Retrieves all favorite movie items.
     *
     * @return A [Flow] emitting a list of favorite [DbMovie] items.
     */
    @Query("SELECT * FROM movies WHERE isFavorite = 1")
    fun getAllFavoriteItems(): Flow<List<DbMovie>>

    /**
     * Updates the favorite status of a movie item.
     *
     * @param movieId The ID of the movie to update.
     * @param isFavorite The new favorite status.
     */
    @Query("UPDATE movies SET isFavorite = :isFavorite WHERE id = :movieId")
    suspend fun updateFavorite(movieId: Int, isFavorite: Boolean)

    /**
     * Retrieves movie items that match the specified query.
     *
     * @param query The search query.
     * @return A [Flow] emitting a list of [DbMovie] items that match the query.
     */
    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%'")
    fun getItemsByQuery(query: String): Flow<List<DbMovie>>

    /**
     * Deletes a movie item from the database.
     *
     * @param item The [DbMovie] item to be deleted.
     */
    @Delete
    suspend fun delete(item: DbMovie)
}
