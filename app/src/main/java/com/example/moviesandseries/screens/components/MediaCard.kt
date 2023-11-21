package com.example.moviesandseries.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.example.moviesandseries.domain.movie.MovieIndex
import com.example.moviesandseries.network.ApiEndpoints

@Composable
fun MediaCard(movie: MovieIndex, onMovieClick: (Int) -> Unit) {
    // Customize this composable based on how you want to display each movie item
    ElevatedCard(
        modifier = Modifier
            .clickable(onClick = { onMovieClick(movie.id) }),
        shape = RoundedCornerShape(12.dp),

    ) {
        Box() {
            RatingComposable(rating = movie.voteAverage, modifier = Modifier.zIndex(2f))

            val painter = rememberAsyncImagePainter(
                model = ApiEndpoints.Poster + movie.posterPath,
                error = rememberVectorPainter(image = Icons.Filled.BrokenImage),
                placeholder = rememberVectorPainter(image = Icons.Filled.LocalMovies),
            )
            Image(
                painter = painter,
                contentDescription = movie.title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2 / 3f),
                alignment = Alignment.Center,
            )
        }
    }
}
