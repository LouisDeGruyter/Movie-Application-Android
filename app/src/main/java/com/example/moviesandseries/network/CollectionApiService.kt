package com.example.moviesandseries.network

import com.example.moviesandseries.model.collection.CollectionDetailApi
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionApiService {
    @GET(ApiEndpoints.Collections)
    suspend fun getCollectionDetail(@Path("collection_id") collectionId: Int): CollectionDetailApi
}