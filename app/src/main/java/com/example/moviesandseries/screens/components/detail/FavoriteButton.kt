package com.example.moviesandseries.screens.components.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * Composable function to display a favorite button.
 *
 * @param isFavorite Boolean indicating whether the item is marked as a favorite.
 * @param onFavoriteClick Callback function to be invoked when the favorite button is clicked.
 * @param modifier Modifier for customizing the layout.
 */
@Composable
fun FavoriteButton(isFavorite: Boolean, onFavoriteClick: () -> Unit, modifier: Modifier) {
    // IconButton with a filled or outlined heart icon based on the isFavorite state
    IconButton(onClick = { onFavoriteClick() }, modifier = modifier) {
        Icon(
            if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = if (isFavorite) "Favorite" else "Not Favorite",
            modifier = Modifier.fillMaxSize(),
            tint = if (isFavorite) Color.Red else MaterialTheme.colorScheme.onPrimary,
        )
    }
}
