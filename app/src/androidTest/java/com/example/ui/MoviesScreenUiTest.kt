package com.example.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.moviesandseries.screens.movies.list.MoviesScreen
import com.example.moviesandseries.util.MoviesAndSeriesNavigationType
import org.junit.Rule
import org.junit.Test

class MoviesScreenUiTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun moviesScreenContentTest() {
        composeTestRule.setContent {
            MoviesScreen(onMovieClick = { }, navigationType = MoviesAndSeriesNavigationType.BOTTOM_NAVIGATION)
        }
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag("moviesGrid"))
        composeTestRule.onNodeWithTag("moviesGrid").assertIsDisplayed()
    }
}
