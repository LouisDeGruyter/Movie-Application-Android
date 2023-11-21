package com.example.moviesandseries.screens.components.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
private val GRID_SPACING= 10.dp
@Composable
fun LoadingMediaGrid(numberOfItems: Int, columns:Int) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        contentPadding = PaddingValues(
            start = GRID_SPACING,
            end = GRID_SPACING,
            top = GRID_SPACING,
            bottom = GRID_SPACING
        ),
        horizontalArrangement = Arrangement.spacedBy(GRID_SPACING, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(GRID_SPACING, Alignment.CenterVertically),
        content = {
            items(numberOfItems) {
                LoadingCard()
            }
        }
    )

}

