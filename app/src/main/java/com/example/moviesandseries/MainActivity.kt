package com.example.moviesandseries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesandseries.screens.MovieAndSeriesApp
import com.example.moviesandseries.ui.theme.MoviesAndSeriesTheme
import com.example.moviesandseries.util.MoviesAndSeriesNavigationType

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAndSeriesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                ) {
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


