package com.example.moviesandseries.screens.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable

@Composable
fun LoadingRow(numberOfItems: Int) {
    LazyRow(content = {
        items(numberOfItems) {
            LoadingCard()
        }
    })
}

@Composable
fun LoadingCard() {
    TODO("Not yet implemented")
}
