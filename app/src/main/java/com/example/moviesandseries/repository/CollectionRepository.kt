package com.example.moviesandseries.repository

import android.util.Log
import com.example.moviesandseries.domain.Collection
import com.example.moviesandseries.model.collection.asDomainObject
import com.example.moviesandseries.network.CollectionApiService
import java.net.SocketTimeoutException

interface CollectionRepository {
    suspend fun getCollectionDetail(collectionId: Int): Collection
}
class NetworkCollectionRepository(
    private val collectionApiService: CollectionApiService,
) : CollectionRepository {
    override suspend fun getCollectionDetail(collectionId: Int): Collection = try {
        collectionApiService.getCollectionDetail(collectionId).asDomainObject()
    } catch (e: SocketTimeoutException) {
        Log.e("SocketTimeoutException", "SocketTimeoutException")
        Collection()
    } catch (e: Exception) {
        Log.e("Exception", e.message.toString())
        Collection()
    }
}
