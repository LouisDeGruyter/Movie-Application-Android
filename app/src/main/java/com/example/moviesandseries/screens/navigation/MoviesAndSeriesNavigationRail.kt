package com.example.moviesandseries.screens.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination

/**
 * Composable function representing the navigation rail for Movies and Series app.
 *
 * @param selectedDestination The currently selected navigation destination.
 * @param onTabPressed The callback function to be invoked when a navigation tab is pressed.
 * @param modifier The modifier for customization of the navigation rail.
 */
@Composable
fun MoviesAndSeriesNavigationRail(
    selectedDestination: NavDestination?,
    onTabPressed: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier = modifier) {
        for (navItem in MoviesAndSeriesAppNavigation.entries) {
            NavigationRailItem(
                selected = selectedDestination?.route == navItem.route,
                onClick = { onTabPressed(navItem.route) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.contentDescription,
                    )
                },
            )
        }
    }
}
