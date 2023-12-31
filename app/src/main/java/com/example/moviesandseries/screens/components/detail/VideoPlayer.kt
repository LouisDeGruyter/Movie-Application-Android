package com.example.moviesandseries.screens.components.detail

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

/**
 * Composable function for displaying a YouTube video using the Android YouTubePlayer library.
 *
 * @param videoId YouTube video ID.
 * @param modifier Modifier for customizing the layout.
 * @param onFullscreen Callback to be invoked when the video enters fullscreen mode.
 */
@Composable
fun YoutubeScreen(
    videoId: String,
    modifier: Modifier,
    onFullscreen: () -> Unit,
) {
    AndroidView(modifier = modifier, factory = {
        val view = YouTubePlayerView(it)

        view.enableAutomaticInitialization = false
        val listener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.cueVideo(videoId, 0f)
            }
        }
        val iFramePlayerOptions: IFramePlayerOptions = IFramePlayerOptions.Builder()
            .controls(1) // enable full screen button
            .fullscreen(1)
            .build()

        view.addFullscreenListener(object : FullscreenListener {
            override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                // the video will continue playing in fullscreenView
                view.visibility = View.GONE
                onFullscreen()

                // optionally request landscape orientation
                // requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
            override fun onExitFullscreen() {
                view.visibility = View.VISIBLE
            }
        })
        view.initialize(listener, iFramePlayerOptions)

        view
    })
}
