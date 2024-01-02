package com.example.moviesandseries.screens.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import com.example.moviesandseries.R

/**
 * Composable function representing the content of the navigation drawer in the Movies and Series app.
 *
 * @param selectedDestination The currently selected destination in the navigation drawer.
 * @param onTabPressed The callback function invoked when a navigation drawer item is pressed.
 * @param modifier The modifier for customization of the NavigationDrawerContent.
 */
@Composable
fun NavigationDrawerContent(
    selectedDestination: NavDestination?,
    onTabPressed: ((String) -> Unit),
    modifier: Modifier = Modifier,
) {
    // Column to hold navigation drawer items
    Column(modifier = modifier) {
        // Iterate through each navigation item and create a NavigationDrawerItem for it
        for (navItem in MoviesAndSeriesAppNavigation.entries) {
            NavigationDrawerItem(
                selected = selectedDestination?.route == navItem.route,
                label = {
                    // Text label for the navigation item
                    Text(
                        text = stringResource(navItem.title),
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.standard_padding)),
                    )
                },
                icon = {
                    // Icon for the navigation item
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.contentDescription,
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    // Set unselected item container color to transparent
                    unselectedContainerColor = Color.Transparent,
                ),
                onClick = { onTabPressed(navItem.route) },
            )
        }
    }
}
