package com.example.moviesandseries.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesandseries.data.database.db.series.DbSeries
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for series-related database operations.
 */
@Dao
interface SeriesDao {

    /**
     * Inserts or replaces a [DbSeries] item in the database.
     *
     * @param item The [DbSeries] item to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbSeries)

    /**
     * Retrieves all series items ordered by popularity in descending order.
     *
     * @return A [Flow] emitting a list of [DbSeries] items ordered by popularity.
     */
    @Query("SELECT * FROM series ORDER BY popularity DESC")
    fun getAllItemsByPopularity(): Flow<List<DbSeries>>

    /**
     * Retrieves all series items ordered by vote average in descending order.
     *
     * @return A [Flow] emitting a list of [DbSeries] items ordered by vote average.
     */
    @Query("SELECT * FROM series ORDER BY voteAverage DESC")
    fun getAllItemsByVoteAverage(): Flow<List<DbSeries>>

    /**
     * Retrieves all series items ordered by first air date in descending order.
     *
     * @return A [Flow] emitting a list of [DbSeries] items ordered by first air date.
     */
    @Query("SELECT * FROM series ORDER BY firstAirDate DESC")
    fun getAllItemsByFirstAirDate(): Flow<List<DbSeries>>

    /**
     * Retrieves a specific series item by its ID.
     *
     * @param seriesId The ID of the series to retrieve.
     * @return A [Flow] emitting the [DbSeries] item with the specified ID, or null if not found.
     */
    @Query("SELECT * FROM series WHERE id = :seriesId")
    fun getItem(seriesId: Int): Flow<DbSeries?>

    /**
     * Retrieves all favorite series items.
     *
     * @return A [Flow] emitting a list of favorite [DbSeries] items.
     */
    @Query("SELECT * FROM series WHERE isFavorite = 1")
    fun getAllFavoriteItems(): Flow<List<DbSeries>>

    /**
     * Updates the favorite status of a series item.
     *
     * @param seriesId The ID of the series to update.
     * @param isFavorite The new favorite status.
     */
    @Query("UPDATE series SET isFavorite = :isFavorite WHERE id = :seriesId")
    suspend fun updateFavorite(seriesId: Int, isFavorite: Boolean)

    /**
     * Retrieves series items that match the specified query.
     *
     * @param query The search query.
     * @return A [Flow] emitting a list of [DbSeries] items that match the query.
     */
    @Query("SELECT * FROM series WHERE name LIKE '%' || :query || '%'")
    fun getItemsByQuery(query: String): Flow<List<DbSeries>>

    /**
     * Deletes a series item from the database.
     *
     * @param item The [DbSeries] item to be deleted.
     */
    @Delete
    suspend fun delete(item: DbSeries)
}
