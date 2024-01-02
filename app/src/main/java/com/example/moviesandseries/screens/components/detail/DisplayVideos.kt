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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesandseries.R
import com.example.moviesandseries.model.videos.Video

/**
 * Composable function to display a list of videos.
 *
 * @param videos List of Video objects representing the videos to be displayed.
 * @param modifier Modifier for customizing the layout.
 * @param onFullScreen Callback for handling full-screen video click.
 */
@Composable
fun DisplayVideos(videos: List<Video>, modifier: Modifier = Modifier, onFullScreen: () -> Unit) {
    // Filter and sort the videos based on the criteria (YouTube and type "Trailer")
    val videosSorted = videos.filter { it.site.lowercase() == "youtube" }.sortedByDescending { it.type == "Trailer" }

    // Return if there are no valid videos
    if (videosSorted.isEmpty()) return

    // Display the list of videos in a column
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.End
    ) {
        // Display the section title
        Text(
            text = "Videos",
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
            modifier = modifier.fillMaxWidth(0.95f),
        )

        // Display the videos in a LazyRow
        LazyRow(modifier = modifier.fillMaxWidth()) {
            items(videosSorted.size) {
                // Add space between video items
                Spacer(modifier = Modifier.width(18.dp))
                // Use the YoutubeScreen composable to display each YouTube video
                YoutubeScreen(
                    videoId = videosSorted[it].key,
                    modifier = Modifier.width(300.dp),
                    onFullscreen = onFullScreen
                )
            }
        }
    }
}
