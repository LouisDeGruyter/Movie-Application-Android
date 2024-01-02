package com.example.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import com.example.moviesandseries.screens.series.detail.SeriesDetailScreen
import com.example.moviesandseries.util.MoviesAndSeriesNavigationType

class SeriesDetailUiTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun seriesDetailsScreenContentTest() {
        composeTestRule.setContent {
            SeriesDetailScreen(seriesId = "85271", backButton = { }, onMovieClick = { }, onSeriesClick = { }, navigationType = MoviesAndSeriesNavigationType.BOTTOM_NAVIGATION)
        }
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag("SeriesDetailScreen"), 10000)
        composeTestRule.onNodeWithTag("SeriesDetailScreen").assertIsDisplayed()
        composeTestRule.onNodeWithText("Recommended").assertIsDisplayed()
        composeTestRule.onNodeWithText("Videos").assertIsDisplayed()
        composeTestRule.onNodeWithText("Production Companies").assertIsDisplayed()
        composeTestRule.onNodeWithText("Actors").assertIsDisplayed()
    }
}