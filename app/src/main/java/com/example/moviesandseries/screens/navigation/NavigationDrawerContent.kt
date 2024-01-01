package com.example.moviesandseries.screens.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerContent(
    selectedDestination: NavDestination?,
    onTabPressed: ((String) -> Unit),
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        for (navItem in MoviesAndSeriesAppNavigation.entries) {
            NavigationDrawerItem(
                selected = selectedDestination?.route == navItem.route,
                label = {
                    Text(
                        text = stringResource(navItem.title),
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.standard_padding)),
                    )
                },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.contentDescription,
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent,
                ),
                onClick = { onTabPressed(navItem.route) },
            )
        }
    }
}
