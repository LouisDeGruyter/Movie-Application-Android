package com.example.moviesandseries.screens.components.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.moviesandseries.model.movie.MovieIndex
private val GRID_SPACING= 10.dp
@Composable
fun LazyGridMovies(movies: LazyPagingItems<MovieIndex>, onMovieClick: (movieId: Int) -> Unit, columns:Int) {
    val lazyGridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        state = lazyGridState,
        contentPadding = PaddingValues(
            start = GRID_SPACING,
            end = GRID_SPACING,
            top = GRID_SPACING,
            bottom = GRID_SPACING
        ),
        horizontalArrangement = Arrangement.spacedBy(GRID_SPACING, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(GRID_SPACING, Alignment.CenterVertically),
        content = {
            items(movies.itemCount) { movie ->
                movie?.let {
                    movies[it]?.let { it1 -> MovieCard(movie = it1, onMovieClick = onMovieClick) }
                }
            }

        }
    )

}