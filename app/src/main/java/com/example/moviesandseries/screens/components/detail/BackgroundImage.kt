package com.example.moviesandseries.screens.components.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.moviesandseries.network.ApiEndpoints

@Composable
fun BackgroundImage(imagePath: String, title: String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(ApiEndpoints.Poster + imagePath)
            .crossfade(500)
            .build(),
        error = rememberVectorPainter(image = Icons.Filled.BrokenImage),
    )
    val fadeColor = if (isSystemInDarkTheme()) {
        Color.Black
    } else {
        Color.White
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painter,
            contentDescription = title,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize(),
        )

        // Add a gradient to create a fading effect at the bottom
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(0.7f to Color.Transparent, 1f to fadeColor),
                ),
        )
    }
}
