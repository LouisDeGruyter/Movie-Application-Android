package com.example.moviesandseries.screens.components.detail.movie

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import com.example.moviesandseries.R
import com.example.moviesandseries.domain.movie.MovieDetail
import com.example.moviesandseries.screens.components.MediaImage
import com.example.moviesandseries.screens.components.detail.BackgroundImage

@Composable
fun MovieDetailComposable(movie: MovieDetail,backButton: @Composable () -> Unit) {
    Card(
        modifier = Modifier.height(dimensionResource(id = R.dimen.backdrop_height)),
        shape = RectangleShape,
    ) {
        BackgroundImage(imagePath = movie.backdropPath, title = movie.title)
    }
    Row() {
        backButton()
        MediaImage(imagePath = movie.posterPath, title = movie.title)
    }
}
