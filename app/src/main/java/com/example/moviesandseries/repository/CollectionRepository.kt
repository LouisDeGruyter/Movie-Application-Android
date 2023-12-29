package com.example.moviesandseries.repository

import com.example.moviesandseries.domain.Collection.CollectionDetail
import com.example.moviesandseries.model.collection.asDomainObject
import com.example.moviesandseries.network.CollectionApiService

interface CollectionRepository {
    suspend fun getCollectionDetail(collectionId: Int): CollectionDetail
}
class NetworkCollectionRepository(
    private val collectionApiService: CollectionApiService,
) : CollectionRepository {
    override suspend fun getCollectionDetail(collectionId: Int): CollectionDetail {
        val collectionDetailApi = collectionApiService.getCollectionDetail(collectionId)
        return collectionDetailApi.asDomainObject()
    }
}
