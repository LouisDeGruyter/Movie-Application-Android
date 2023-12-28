package com.example.moviesandseries.screens.components.index.mediaCard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.moviesandseries.screens.components.TwoByThreeAspectRatioImage

@Composable
fun MediaCard(modifier: Modifier = Modifier, title: String, imagePath: String, rating: Double, onMediaClick: () -> Unit = {}) {
    val interactionSource = remember { MutableInteractionSource() }
    ElevatedCard(modifier = modifier.clickable(indication = null, interactionSource = interactionSource, onClick = { onMediaClick() }).aspectRatio(2 / 3f), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(12.dp)) {
        Box {
            RatingComposable(rating = rating, modifier = Modifier.zIndex(2f).fillMaxWidth())
            TwoByThreeAspectRatioImage(imagePath = imagePath, title = title)
        }
    }
}
