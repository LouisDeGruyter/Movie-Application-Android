package com.example.moviesandseries.screens.components.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.moviesandseries.R
import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.screens.components.index.mediaCard.MediaCard

/**
 * Composable function to display a list of recommended media items.
 *
 * @param recommendedMedia LazyPagingItems representing the recommended media items.
 * @param onMovieClick Callback for handling movie item clicks.
 * @param onSeriesClick Callback for handling series item clicks.
 */
@Composable
fun DisplayRecommended(
    recommendedMedia: LazyPagingItems<MediaIndex>,
    onMovieClick: (movieId: Int) -> Unit,
    onSeriesClick: (seriesId: Int) -> Unit
) {
    // Return if there are no recommended media items
    if (recommendedMedia.itemCount == 0) return

    // Check the loading state and display appropriate content
    when (recommendedMedia.loadState.refresh) {
        is LoadState.Loading -> {
            Text(text = "Loading")
        }

        is LoadState.Error -> {
            Text(text = "Error")
        }

        else -> {
            // Display the list of recommended media items in a column
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.End,
            ) {
                // Display the section title
                Text(
                    text = "Recommended",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 28.sp,
                    fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
                    modifier = Modifier.fillMaxWidth(0.95f),
                )

                // Display the recommended media items in a LazyRow
                LazyRow(
                    content = {
                        items(recommendedMedia.itemCount) { index ->
                            recommendedMedia[index]?.let {
                                // Add space between media items
                                Spacer(modifier = Modifier.width(18.dp))
                                // Use the MediaCard composable to display each media item
                                MediaCard(
                                    title = it.title,
                                    imagePath = it.posterPath,
                                    rating = it.voteAverage,
                                    onMediaClick = {
                                        // Determine the media type and invoke the appropriate click callback
                                        if (it.mediaType == "movie") {
                                            onMovieClick(it.id)
                                        } else {
                                            onSeriesClick(it.id)
                                        }
                                    },
                                    modifier = Modifier.width(
                                        dimensionResource(id = R.dimen.detail_card_width),
                                    ),
                                )
                            }
                        }
                    },
                )
            }
        }
    }
}
