package com.example.repository

import com.example.moviesandseries.model.series.season.SeasonDetailApi
import com.example.moviesandseries.model.series.season.asDomainObject
import com.example.moviesandseries.network.SeasonApiService
import com.example.moviesandseries.repository.NetworkSeasonRepository
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
class NetworkSeasonRepositoryTest {

    @Mock
    private lateinit var seasonApiService: SeasonApiService

    @InjectMocks
    private lateinit var networkSeasonRepository: NetworkSeasonRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test getSeasonDetail`() = runBlocking {
        val seasonDetailApi = SeasonDetailApi(
            airDate = "2023-01-01",
            id = 123,
            name = "Spring Season",
            overview = "Exciting episodes await!",
            posterPath = "/poster.jpg",
            seasonNumber = 2,
            voteAverage = 9.0,
            episodes = listOf(),
        )
        `when`(seasonApiService.getSeasonDetail(1, 1)).thenReturn(seasonDetailApi)
        val result = networkSeasonRepository.getSeasonDetail(1, 1)
        assertEquals(seasonDetailApi.asDomainObject(), result)
    }
}
