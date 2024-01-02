package com.example.moviesandseries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.example.moviesandseries.screens.MovieAndSeriesApp
import com.example.moviesandseries.ui.theme.MoviesAndSeriesTheme
import com.example.moviesandseries.util.MoviesAndSeriesNavigationType

/**
 * MainActivity for the MoviesAndSeries app, responsible for setting up the Compose content.
 */
class MainActivity : ComponentActivity() {
    /**
     * Overrides the onCreate method to set up the Compose content for the activity.
     */
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAndSeriesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    // Determine the window size class to select the appropriate navigation type
                    val windowSize = calculateWindowSizeClass(this)
                    when (windowSize.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            MovieAndSeriesApp(MoviesAndSeriesNavigationType.BOTTOM_NAVIGATION)
                        }
                        WindowWidthSizeClass.Medium -> {
                            MovieAndSeriesApp(MoviesAndSeriesNavigationType.NAVIGATION_RAIL)
                        }
                        WindowWidthSizeClass.Expanded -> {
                            MovieAndSeriesApp(MoviesAndSeriesNavigationType.PERMANENT_NAVIGATION_DRAWER)
                        }

                        else -> {
                            MovieAndSeriesApp(MoviesAndSeriesNavigationType.PERMANENT_NAVIGATION_DRAWER)
                        }
                    }
                }
            }
        }
    }
}


