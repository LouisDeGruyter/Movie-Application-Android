package com.example.moviesandseries.network

import com.example.moviesandseries.model.collection.CollectionDetailApi
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit service interface for making API requests related to collections.
 */
interface CollectionApiService {

    /**
     * Retrieves details for a specific collection based on the provided collection ID.
     *
     * @param collectionId The unique identifier of the collection.
     * @return A suspend function returning the [CollectionDetailApi] containing details of the collection.
     */
    @GET(ApiEndpoints.Collections)
    suspend fun getCollectionDetail(@Path("collection_id") collectionId: Int): CollectionDetailApi
}
