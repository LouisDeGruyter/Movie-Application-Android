package com.example.moviesandseries.screens.components.detail

import android.util.Log
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
import com.example.moviesandseries.screens.components.videoplayer.YoutubeScreen

@Composable
fun DisplayVideos(videos: List<Video>, modifier: Modifier = Modifier, onFullScreen: () -> Unit) {
    val videosSorted = videos.filter { it.site.lowercase() == "youtube" }.sortedByDescending { it.type == "Trailer" }
    if(videosSorted.isEmpty()) return
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = "Videos",
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
            modifier = modifier.fillMaxWidth(0.95f),
        )
        LazyRow(modifier = modifier.fillMaxWidth()) {
            items(videosSorted.size) {
                Spacer(modifier = Modifier.width(18.dp))
                YoutubeScreen(
                    videoId = videosSorted[it].key,
                    modifier = Modifier.width(300.dp),
                    onFullscreen = onFullScreen
                )
            }
        }
    }
}