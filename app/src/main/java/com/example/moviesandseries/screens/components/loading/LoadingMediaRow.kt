package com.example.moviesandseries.screens.components.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.moviesandseries.R

/**
 * Composable function for displaying a row of loading cards.
 *
 * @param numberOfItems The total number of loading cards to be displayed.
 * @param modifier Modifier for customizing the layout and appearance of the loading row.
 */
@Composable
fun LoadingMediaRow(numberOfItems: Int, modifier: Modifier) {
    val rowSpacing = dimensionResource(id = R.dimen.row_spacing)
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(rowSpacing, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        content = {
            items(numberOfItems) {
                LoadingCard()
            }
        },
    )
}
