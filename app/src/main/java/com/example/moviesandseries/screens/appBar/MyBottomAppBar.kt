package com.example.templateapplication.screens.appBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable function for displaying a custom BottomAppBar with navigation icons.
 *
 * @param onHome Callback when the home icon is clicked.
 * @param onMovies Callback when the movies icon is clicked.
 * @param onSeries Callback when the series icon is clicked.
 */
@Composable
fun MyBottomAppBar(
    onHome: () -> Unit,
    onMovies: () -> Unit,
    onSeries: () -> Unit,
) {
    BottomAppBar(
        modifier = Modifier.heightIn(30.dp, 40.dp),
        actions = {
            // Row to contain the navigation icons with centered horizontal arrangement
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                // Home Icon
                IconButton(onClick = onHome) {
                    Icon(Icons.Filled.Home, contentDescription = "Navigate to home")
                }
                // Movies Icon
                IconButton(onClick = onMovies) {
                    Icon(Icons.Filled.LocalMovies, contentDescription = "Navigate to movies")
                }
                // Series Icon
                IconButton(onClick = onSeries) {
                    Icon(Icons.Filled.Movie, contentDescription = "Navigate to series")
                }
            }
        },
    )
}
