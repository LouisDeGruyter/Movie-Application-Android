package com.example.moviesandseries.repository

import android.util.Log
import com.example.moviesandseries.domain.Collection
import com.example.moviesandseries.model.collection.asDomainObject
import com.example.moviesandseries.network.CollectionApiService
import java.net.SocketTimeoutException

/**
 * Repository interface for fetching details of a collection.
 */
interface CollectionRepository {
    /**
     * Fetches details of a collection by its ID.
     *
     * @param collectionId The ID of the collection to fetch details for.
     * @return [Collection] object representing the details of the collection.
     */
    suspend fun getCollectionDetail(collectionId: Int): Collection
}

/**
 * Implementation of [CollectionRepository] that uses the network API to fetch collection details.
 *
 * @param collectionApiService The service responsible for making network calls related to collections.
 */
class NetworkCollectionRepository(
    private val collectionApiService: CollectionApiService,
) : CollectionRepository {

    /**
     * Fetches details of a collection by its ID using the network API.
     *
     * @param collectionId The ID of the collection to fetch details for.
     * @return [Collection] object representing the details of the collection.
     */
    override suspend fun getCollectionDetail(collectionId: Int): Collection = try {
        collectionApiService.getCollectionDetail(collectionId).asDomainObject()
    } catch (e: SocketTimeoutException) {
        // Handle SocketTimeoutException
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        Collection()
    } catch (e: Exception) {
        // Handle other exceptions
        Log.e("Exception", e.message.toString())
        Collection()
    }
}
