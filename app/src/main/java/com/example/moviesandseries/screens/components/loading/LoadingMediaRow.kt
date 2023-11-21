package com.example.moviesandseries.screens.components.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val ROW_SPACING= 12.dp
@Composable
fun LoadingMediaRow(numberOfItems: Int, modifier: Modifier) {
    LazyRow(modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(ROW_SPACING, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        content = {
        items(numberOfItems) {
            LoadingCard()
        }
    })
}

