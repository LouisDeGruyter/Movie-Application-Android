package com.example.domain.movie

import com.example.moviesandseries.domain.Dates
import com.example.moviesandseries.domain.movie.Movie
import com.example.moviesandseries.domain.movie.MovieContainerWithDates
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieContainerWithDatesTest {

    @Test
    fun `create MovieContainerWithDates with default values`() {
        val movieContainerWithDates = MovieContainerWithDates()

        assertEquals(Dates(), movieContainerWithDates.dates)
        assertEquals(1, movieContainerWithDates.page)
        assertEquals(emptyList<Movie>(), movieContainerWithDates.results)
        assertEquals(1, movieContainerWithDates.totalPages)
        assertEquals(0, movieContainerWithDates.totalResults)
    }

    @Test
    fun `create MovieContainerWithDates with specific values`() {
        val movie1 = Movie(id = 1, title = "Movie 1")
        val movie2 = Movie(id = 2, title = "Movie 2")
        val dates = Dates(minimum = "2023-01-01", maximum = "2023-12-31")

        val movieContainerWithDates = MovieContainerWithDates(
            dates = dates,
            page = 2,
            results = listOf(movie1, movie2),
            totalPages = 3,
            totalResults = 15,
        )

        assertEquals(dates, movieContainerWithDates.dates)
        assertEquals(2, movieContainerWithDates.page)
        assertEquals(listOf(movie1, movie2), movieContainerWithDates.results)
        assertEquals(3, movieContainerWithDates.totalPages)
        assertEquals(15, movieContainerWithDates.totalResults)
    }

    // Add more tests as needed
}
