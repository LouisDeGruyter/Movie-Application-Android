package com.example.moviesandseries.screens.components.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.moviesandseries.screens.components.index.mediaCard.RatingComposable

@Composable
fun LoadingCard() {
    ElevatedCard(shape = RoundedCornerShape(12.dp)) {
        Box {
            RatingComposable(rating = 7.5, modifier = Modifier.zIndex(2f).aspectRatio(2 / 3f))
            Image(
                painter = rememberVectorPainter(image = Icons.Filled.LocalMovies),
                contentDescription = "Loading",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2 / 3f),
                alignment = Alignment.Center,
            )
        }
    }
}
