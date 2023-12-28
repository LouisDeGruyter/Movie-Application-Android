package com.example.moviesandseries.screens.components.videoplayer

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun VideoThumbnail(modifier: Modifier = Modifier, videoUrl: String, videoTitle: String, onVideoClick: () -> Unit = {}) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(modifier = modifier.clickable(indication = null, interactionSource = interactionSource, onClick = { onVideoClick() }).aspectRatio(16 / 9f)) {
        val imageUrl = "https://img.youtube.com/vi/$videoUrl/hqdefault.jpg"
        Log.d("videothumbnail", "imageUrl: $imageUrl")
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(500)
                .build(),
            error = rememberVectorPainter(image = Icons.Filled.BrokenImage),
        )
        Image(
            painter = painter,
            contentDescription = videoTitle,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().aspectRatio(16/9f),
        )
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = null,
            tint= Color.White,
            modifier = Modifier.align(Alignment.Center).zIndex(2f).size(80.dp),
        )
    }
}
