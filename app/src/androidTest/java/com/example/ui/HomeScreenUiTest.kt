package com.example.ui

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.moviesandseries.screens.home.HomeScreen
import org.junit.Rule
import org.junit.Test

class HomeScreenUiTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreenContentTest() {
        composeTestRule.setContent {
            HomeScreen(onMovieClick = { Unit }, onSeriesClick = { Unit })
        }

        composeTestRule.onNodeWithText("Movies in Theater").isDisplayed()
        composeTestRule.onNodeWithText("Series Airing Today").isDisplayed()
        composeTestRule.onNodeWithText("Popular Movies").isDisplayed()
        composeTestRule.onNodeWithText("Popular Series").isDisplayed()
        composeTestRule.onNodeWithText("Top Rated Movies").isDisplayed()
        composeTestRule.onNodeWithText("Top Rated Series").isDisplayed()
        composeTestRule.onNodeWithText("Upcoming Movies").isDisplayed()
        composeTestRule.onNodeWithText("On the Air Series").isDisplayed()
    }
}
