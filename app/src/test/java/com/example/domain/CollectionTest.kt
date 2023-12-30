package com.example.domain

import com.example.moviesandseries.domain.Collection
import com.example.moviesandseries.domain.movie.Movie
import org.junit.Assert.assertEquals
import org.junit.Test

class CollectionTest {

    @Test
    fun `create CollectionDetail with default values`() {
        val collection = Collection()

        assertEquals("", collection.backdropPath)
        assertEquals(0, collection.id)
        assertEquals("", collection.name)
        assertEquals("", collection.overview)
        assertEquals(emptyList<Movie>(), collection.parts)
        assertEquals("", collection.posterPath)
    }

    @Test
    fun `create CollectionDetail with specific values`() {
        val movie1 = Movie(id = 1, title = "Movie 1")
        val movie2 = Movie(id = 2, title = "Movie 2")

        val collection = Collection(
            backdropPath = "backdropPath",
            id = 42,
            name = "Collection Name",
            overview = "Collection Overview",
            parts = listOf(movie1, movie2),
            posterPath = "posterPath",
        )

        assertEquals("backdropPath", collection.backdropPath)
        assertEquals(42, collection.id)
        assertEquals("Collection Name", collection.name)
        assertEquals("Collection Overview", collection.overview)
        assertEquals(listOf(movie1, movie2), collection.parts)
        assertEquals("posterPath", collection.posterPath)
    }
}
