package com.example.moviesandseries.screens.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Composable function for a back button with an arrow icon.
 *
 * @param modifier Modifier for customizing the layout and appearance of the back button.
 * @param onBackPressed Callback function to be invoked when the back button is clicked.
 */
@Composable
fun BackButton(modifier: Modifier = Modifier, onBackPressed: () -> Unit) {
    IconButton(onClick = onBackPressed, modifier = modifier) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back Button", modifier = Modifier.fillMaxSize())
    }
}
