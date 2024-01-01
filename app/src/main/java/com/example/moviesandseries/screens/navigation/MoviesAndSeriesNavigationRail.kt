package com.example.moviesandseries.screens.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination

@Composable
fun MoviesAndSeriesNavigationRail(selectedDestination: NavDestination?, onTabPressed: (String) -> Unit, modifier : Modifier = Modifier){
    NavigationRail(modifier = modifier) {
        for (navItem in MoviesAndSeriesAppNavigation.entries) {
            NavigationRailItem(
                selected = selectedDestination?.route == navItem.name,
                onClick = { onTabPressed(navItem.name) },
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