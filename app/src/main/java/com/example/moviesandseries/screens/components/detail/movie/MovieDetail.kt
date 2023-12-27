package com.example.moviesandseries.screens.components.detail.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.moviesandseries.R
import com.example.moviesandseries.domain.movie.MovieDetail
import com.example.moviesandseries.screens.components.detail.BackgroundImage
import com.example.moviesandseries.screens.components.index.mediaCard.MediaCard

@Composable
fun MovieDetailComposable(movie: MovieDetail, backButton: @Composable (modifier: Modifier) -> Unit) {
    ConstraintLayout() {
        val (backdrop, mediaCard, backButtonRef, title) = createRefs()
        Card(
            modifier = Modifier.fillMaxHeight(0.4f).constrainAs(backdrop) { top.linkTo(parent.top); absoluteLeft.linkTo(parent.absoluteLeft); absoluteRight.linkTo(parent.absoluteRight) },
            shape = RectangleShape,
        ) {
            BackgroundImage(imagePath = movie.backdropPath, title = movie.title)
        }
        // center mediacard on the bottom of the backdrop
        MediaCard(title = movie.title, imagePath = movie.posterPath, rating = movie.voteAverage, modifier = Modifier.fillMaxWidth(0.5f).zIndex(2f).constrainAs(mediaCard) { top.linkTo(backdrop.bottom); bottom.linkTo(backdrop.bottom); absoluteLeft.linkTo(parent.absoluteLeft); absoluteRight.linkTo(parent.absoluteRight) })
        Box(modifier = Modifier.constrainAs(backButtonRef) { absoluteRight.linkTo(mediaCard.absoluteLeft); absoluteLeft.linkTo(parent.absoluteLeft); top.linkTo(backdrop.bottom, margin = 15.dp) }) {
            backButton(Modifier.height(40.dp))
        }
        Column(modifier = Modifier.constrainAs(title) { top.linkTo(mediaCard.bottom); absoluteLeft.linkTo(parent.absoluteLeft); absoluteRight.linkTo(parent.absoluteRight)}.fillMaxWidth(0.9f)) {
            Text(
                text = movie.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),

            )
        }
    }
}
