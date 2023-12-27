package com.example.moviesandseries.screens.components.index.mediaCard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.moviesandseries.screens.components.MediaImage

@Composable
fun MediaCard(modifier: Modifier = Modifier, title: String, imagePath: String, rating: Double, onMediaClick: () -> Unit = {}) {
    ElevatedCard(modifier = modifier.clickable(onClick = { onMediaClick() }).aspectRatio(2 / 3f), shape = RoundedCornerShape(12.dp)) {
        Box {
            RatingComposable(rating = rating, modifier = Modifier.zIndex(2f).fillMaxWidth())
            MediaImage(imagePath = imagePath, title = title)
        }
    }
}