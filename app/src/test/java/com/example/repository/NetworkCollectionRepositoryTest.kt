package com.example.repository

import com.example.moviesandseries.model.collection.CollectionDetailApi
import com.example.moviesandseries.model.collection.asDomainObject
import com.example.moviesandseries.network.CollectionApiService
import com.example.moviesandseries.repository.NetworkCollectionRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class NetworkCollectionRepositoryTest {

    @Mock
    private lateinit var collectionApiService: CollectionApiService

    @InjectMocks
    private lateinit var networkCollectionRepository: NetworkCollectionRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test getCollectionDetail`() = runBlocking {
        val collectionDetailApi = CollectionDetailApi(
            backdropPath = "/backdropPath1",
            id = 1,
            name = "collection1",
            overview = "overview1",
            parts = listOf(),
        )
        `when`(collectionApiService.getCollectionDetail(1)).thenReturn(collectionDetailApi)
        val result = networkCollectionRepository.getCollectionDetail(1)
        assertEquals(collectionDetailApi.asDomainObject(), result)
    }
}
