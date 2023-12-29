package com.example.moviesandseries.screens.components.index

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import androidx.paging.compose.LazyPagingItems
import com.example.moviesandseries.R
import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.screens.components.index.mediaCard.MediaCard

@Composable
fun LazyPagingMediaGrid(media: LazyPagingItems<MediaIndex>, onMediaClick: (movieId: Int) -> Unit, columns: Int) {
    val lazyGridState = rememberLazyGridState()
    val gridSpacing = dimensionResource(id = R.dimen.grid_spacing)
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        state = lazyGridState,
        contentPadding = PaddingValues(
            start = gridSpacing,
            end = gridSpacing,
            top = gridSpacing,
            bottom = gridSpacing,
        ),
        horizontalArrangement = Arrangement.spacedBy(gridSpacing, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(gridSpacing, Alignment.CenterVertically),
        content = {
            items(media.itemCount) { movie ->
                media[movie]?.let { MediaCard(title = it.title, imagePath = it.posterPath ?: "", rating = it.voteAverage, onMediaClick = { onMediaClick(it.id) },) }
            }
        },
    )
}
