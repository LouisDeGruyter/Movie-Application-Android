package com.example.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.moviesandseries.screens.movies.detail.MovieDetailsScreen
import org.junit.Rule
import org.junit.Test

class MovieDetailUiTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun movieDetailsScreenContentTest() {
        composeTestRule.setContent {
            MovieDetailsScreen(movieId = "695721", backButton = { }, onMovieClick = { }, onSeriesClick = { })
        }
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag("MovieDetailScreen"), 10000)
        composeTestRule.onNodeWithTag("MovieDetailScreen").assertIsDisplayed()
        composeTestRule.onNodeWithText("Recommended").assertIsDisplayed()
        composeTestRule.onNodeWithText("Videos").assertIsDisplayed()
        composeTestRule.onNodeWithText("Production Companies").assertIsDisplayed()
        composeTestRule.onNodeWithText("Actors").assertIsDisplayed()
    }
}
