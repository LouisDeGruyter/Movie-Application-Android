package com.example.moviesandseries.screens.components.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

/**
 * Composable function to display an image with error handling.
 *
 * @param imagePath The path of the image to be loaded.
 * @param title Content description for the image.
 */
@Composable
fun BackgroundImage(imagePath: String, title: String) {
    // Create an ImageRequest using Coil's ImageRequest.Builder
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data("https://image.tmdb.org/t/p/original/$imagePath")
        .crossfade(500)
        .build()

    // Use rememberAsyncImagePainter to load the image asynchronously with error handling
    val painter = rememberAsyncImagePainter(
        model = imageRequest,
        // Display a placeholder image in case of loading failure
        error = rememberVectorPainter(image = Icons.Filled.BrokenImage),
    )

    // Display the image using the Image composable
    Image(
        painter = painter,
        contentDescription = title,
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    )
}
