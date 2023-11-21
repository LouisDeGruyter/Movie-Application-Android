package com.example.moviesandseries.screens.components.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.moviesandseries.model.movie.MovieIndex
import com.example.moviesandseries.screens.components.MediaCard

private val ROW_SPACING= 12.dp
@Composable
fun LazyMoviesRow(
    movies: LazyPagingItems<MovieIndex>,
    onMovieClick: (movieId: Int) -> Unit,
    modifier: Modifier
) {
    val lazyListState = rememberLazyListState()
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(ROW_SPACING, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        content ={
            items(movies.itemCount) { movie ->
                movie?.let {
                    movies[it]?.let { it1 -> MediaCard(movie = it1, onMovieClick = onMovieClick) }
                }
            }
        }
    )
}