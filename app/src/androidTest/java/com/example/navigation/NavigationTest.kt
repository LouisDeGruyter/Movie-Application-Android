package com.example.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviesandseries.screens.MovieAndSeriesApp
import com.example.moviesandseries.util.MoviesAndSeriesNavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MovieAndSeriesApp(MoviesAndSeriesNavigationType.BOTTOM_NAVIGATION)
        }
    }

    @Test
    fun verifyStartDestination() {
        composeTestRule
            .onNodeWithText("Home")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToMovies() {
        composeTestRule
            .onNodeWithContentDescription("navigate to movies").performClick()
        composeTestRule
            .onNodeWithText("Movies")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToSeries() {
        composeTestRule
            .onNodeWithContentDescription("navigate to series").performClick()
        composeTestRule
            .onNodeWithText("Series")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToHome() {
        composeTestRule
            .onNodeWithContentDescription("navigate to home").performClick()
        composeTestRule
            .onNodeWithText("Home")
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun navigateToMovieDetailPage() {
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag("Movies"), 10000)
        composeTestRule.onAllNodesWithTag("mediaCard Movies").onFirst().performClick()
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag("MovieDetailScreen"), 10000)
        composeTestRule.onNodeWithTag("MovieDetailScreen").assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun navigateToSeriesDetailPage() {
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag("Series"), 10000)
        composeTestRule.onAllNodesWithTag("Series").onFirst().assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("mediaCard Series").onFirst().performClick()
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag("SeriesDetailScreen"), 10000)
        composeTestRule.onNodeWithTag("SeriesDetailScreen").assertIsDisplayed()
    }
}
