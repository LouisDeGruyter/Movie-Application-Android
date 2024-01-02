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
import com.example.moviesandseries.R
import com.example.moviesandseries.domain.Collection
import com.example.moviesandseries.screens.components.index.mediaCard.MediaCard

/**
 * Composable function to display a collection of media items.
 *
 * @param collection The collection to be displayed.
 * @param onMediaClick Callback for handling media item clicks.
 * @param currentMovieId ID of the current movie to be excluded from the collection.
 */
@Composable
fun DisplayCollection(collection: Collection, onMediaClick: (id: Int) -> Unit, currentMovieId: Int = 0) {
    // Filter out the current movie from the collection
    val filteredCollection = collection.parts.filter { it.id != currentMovieId }

    // Display the collection in a column with spaced arrangement
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.End,
    ) {
        // Display the collection title
        Text(
            text = "More from ${collection.name}",
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
            modifier = Modifier.fillMaxWidth(0.95f),
        )

        // Display the collection items in a LazyRow
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(filteredCollection.size) {
                Spacer(modifier = Modifier.width(18.dp))
                // Extract the media from the filtered collection
                val media = filteredCollection[it]
                // Use the MediaCard composable to display each media item
                MediaCard(
                    title = media.title,
                    imagePath = media.posterPath,
                    rating = media.voteAverage,
                    modifier = Modifier.width(dimensionResource(id = R.dimen.detail_card_width)),
                    onMediaClick = { onMediaClick(media.id) },
                )
            }
        }
    }
}
