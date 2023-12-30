package com.example.domain.movie

import com.example.moviesandseries.domain.movie.Movie
import com.example.moviesandseries.domain.movie.MovieContainer
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieContainerTest {

    @Test
    fun `create MovieContainer with default values`() {
        val movieContainer = MovieContainer()

        assertEquals(1, movieContainer.page)
        assertEquals(emptyList<Movie>(), movieContainer.results)
        assertEquals(1, movieContainer.totalPages)
        assertEquals(0, movieContainer.totalResults)
    }

    @Test
    fun `create MovieContainer with specific values`() {
        val movie1 = Movie(id = 1, title = "Movie 1")
        val movie2 = Movie(id = 2, title = "Movie 2")

        val movieContainer = MovieContainer(
            page = 2,
            results = listOf(movie1, movie2),
            totalPages = 3,
            totalResults = 15,
        )

        assertEquals(2, movieContainer.page)
        assertEquals(listOf(movie1, movie2), movieContainer.results)
        assertEquals(3, movieContainer.totalPages)
        assertEquals(15, movieContainer.totalResults)
    }
}
