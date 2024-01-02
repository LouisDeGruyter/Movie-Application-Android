package com.example.moviesandseries.screens.components.index

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.paging.compose.LazyPagingItems
import com.example.moviesandseries.R
import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.screens.components.index.mediaCard.MediaCard

/**
 * Composable function for displaying a lazy row of media items using Paging 3.
 *
 * @param media LazyPagingItems representing the paginated list of media items.
 * @param onMediaClick Callback for handling media item clicks.
 * @param modifier Modifier for customizing the layout.
 * @param mediaType String representing the type of media (e.g., "Movies").
 */
@Composable
fun LazyPagingMediaRow(media: LazyPagingItems<MediaIndex>, onMediaClick: (movieId: Int) -> Unit, modifier: Modifier, mediaType: String = "Movies") {
    val rowSpacing = dimensionResource(id = R.dimen.row_spacing)
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(rowSpacing, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        content = {
            items(media.itemCount) { movie ->
                media[movie]?.let {
                    MediaCard(title = it.title, imagePath = it.posterPath, rating = it.voteAverage, onMediaClick = { onMediaClick(it.id) }, modifier = Modifier.testTag("mediaCard $mediaType"))
                }
            }
        },
    )
}
