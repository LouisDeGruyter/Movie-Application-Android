package com.example.templateapplication.screens.appBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalMovies
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyBottomAppBar(

    onHome: () -> Unit,
    onMovies: () -> Unit,
    onSeries: () -> Unit,

) {
    BottomAppBar(
        modifier = Modifier.heightIn(30.dp,40.dp),
        actions = {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = onHome) {
                    Icon(Icons.Filled.Home, contentDescription = "Localized description")
                }
                IconButton(onClick = onMovies) {
                    Icon(Icons.Filled.LocalMovies, contentDescription = "Localized description")
                }
                IconButton(onClick = onSeries) {
                    Icon(Icons.Filled.Movie, contentDescription = "Localized description")
                }
            }
        },
    ) }
