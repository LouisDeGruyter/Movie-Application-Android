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


@Composable
fun DisplayRecommended(recommendedMedia: LazyPagingItems<MediaIndex>, onMovieClick: (movieId: Int) -> Unit, onSeriesClick: (seriesId: Int) -> Unit) {
    if (recommendedMedia.itemCount == 0) return
    when (recommendedMedia.loadState.refresh) {
        is LoadState.Loading -> {
            Text(text = "Loading")
        }

        is LoadState.Error -> {
            Text(text = "Error")
        }

        else -> {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = "Recommended",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 28.sp,
                    fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
                    modifier = Modifier.fillMaxWidth(0.95f),
                )
                LazyRow(
                    content = {
                        items(recommendedMedia.itemCount) { index ->
                            recommendedMedia[index]?.let {
                                Spacer(modifier = Modifier.width(18.dp))
                                MediaCard(
                                    title = it.title,
                                    imagePath = it.posterPath,
                                    rating = it.voteAverage,
                                    onMediaClick = {
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