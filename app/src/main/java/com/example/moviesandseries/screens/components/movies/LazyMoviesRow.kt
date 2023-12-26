package com.example.moviesandseries.screens.components.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.paging.compose.LazyPagingItems
import com.example.moviesandseries.R
import com.example.moviesandseries.domain.movie.MovieIndex

@Composable
fun LazyMoviesRow(movies: LazyPagingItems<MovieIndex>, onMovieClick: (movieId: Int) -> Unit, modifier: Modifier) {
    val rowSpacing = dimensionResource(id = R.dimen.row_spacing)
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(rowSpacing, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        content = {
            items(movies.itemCount) { movie ->
                movies[movie]?.let { MediaCard(movie = it, onMovieClick = onMovieClick) }
            }
        },
    )
}
