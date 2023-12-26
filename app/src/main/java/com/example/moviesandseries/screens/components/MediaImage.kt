package com.example.moviesandseries.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.moviesandseries.network.ApiEndpoints

@Composable
fun MediaImage(imagePath: String, title: String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(ApiEndpoints.Poster + imagePath).crossfade(500).build(),
        error = rememberVectorPainter(image = Icons.Filled.BrokenImage),
        placeholder = rememberVectorPainter(image = Icons.Filled.LocalMovies),
    )
    Image(
        painter = painter,
        contentDescription = title,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2 / 3f),
        alignment = Alignment.Center,
    )
}
