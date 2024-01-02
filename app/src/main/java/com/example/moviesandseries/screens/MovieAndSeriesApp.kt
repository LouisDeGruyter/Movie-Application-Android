package com.example.moviesandseries.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviesandseries.R
import com.example.moviesandseries.screens.components.BackButton
import com.example.moviesandseries.screens.navigation.Destinations
import com.example.moviesandseries.screens.navigation.MoviesAndSeriesNavigationRail
import com.example.moviesandseries.screens.navigation.NavigationDrawerContent
import com.example.moviesandseries.screens.navigation.navComponent
import com.example.moviesandseries.util.MoviesAndSeriesNavigationType
import com.example.templateapplication.screens.appBar.MyBottomAppBar
import com.example.templateapplication.screens.appBar.MyTopAppBar

val noTopAppbarRoutes: List<String> = listOf(
    Destinations.MovieDetails.route,
    Destinations.SeriesDetail.route,
)

/**
 * Composable function that defines the main structure of the MoviesAndSeries app based on the specified navigation type.
 *
 * @param navigationType Type of navigation used in the app (e.g., Bottom Navigation, Permanent Navigation Drawer, Navigation Rail).
 * @param navController NavHostController used for navigation within the app.
 */
@Composable
fun MovieAndSeriesApp(navigationType: MoviesAndSeriesNavigationType, navController: NavHostController = rememberNavController()) {
    // navigation

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route
    val currentEnumDestination: Destinations? = currentDestination?.let { route ->
        enumValues<Destinations>().find {
            it.route == route.substring(
                startIndex = 0,
                endIndex = if ("/" in route) route.indexOf("/") else route.length,
            )
        }
    }
    val currentPage = currentEnumDestination?.route ?: ""

    val navigateBack: () -> Unit = { navController.popBackStack() }
    when (navigationType) {
        MoviesAndSeriesNavigationType.BOTTOM_NAVIGATION -> BottomNavigationApp(
            currentPage = currentPage,
            currentDestination = currentDestination ?: "",
            navigateBack = navigateBack,
            navController = navController,
        )

        MoviesAndSeriesNavigationType.PERMANENT_NAVIGATION_DRAWER -> PermanentNavigationDrawerApp(
            currentPage = currentPage,
            currentDestination = currentDestination ?: "",
            navigateBack = navigateBack,
            navController = navController,
        )

        MoviesAndSeriesNavigationType.NAVIGATION_RAIL -> NavigationRailApp(
            currentPage = currentPage,
            currentDestination = currentDestination ?: "",
            navigateBack = navigateBack,
            navController = navController,
        )
    }
}

/**
 * Composable function for the app structure with Bottom Navigation.
 *
 * @param currentPage Current page being displayed.
 * @param currentDestination Current destination being navigated to.
 * @param navigateBack Function to navigate back.
 * @param navController NavHostController used for navigation.
 * @param navigationType Type of navigation used in the app.
 */
@Composable
fun BottomNavigationApp(currentPage: String, currentDestination: String, navigateBack: () -> Unit, navController: NavHostController, navigationType: MoviesAndSeriesNavigationType = MoviesAndSeriesNavigationType.BOTTOM_NAVIGATION) {
    Scaffold(
        topBar = {
            if (currentPage !in noTopAppbarRoutes) {
                MyTopAppBar(currentpage = currentPage) {
                    if (currentDestination != Destinations.Home.route) {
                        BackButton(onBackPressed = navigateBack, modifier = Modifier.height(30.dp))
                    }
                }
            }
        },
        bottomBar = {
            MyBottomAppBar(
                onHome = {
                    navController.popBackStack(
                        Destinations.Home.route,
                        inclusive = false,
                    )
                },
                onMovies = { navController.navigate(Destinations.Movies.route) },
                onSeries = { navController.navigate(Destinations.Series.route) },
            )
        },
    ) { innerPadding ->
        navComponent(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            navigationType = navigationType,
        )
    }
}

/**
 * Composable function for the app structure with Permanent Navigation Drawer.
 *
 * @param currentPage Current page being displayed.
 * @param currentDestination Current destination being navigated to.
 * @param navigateBack Function to navigate back.
 * @param navController NavHostController used for navigation.
 */
@Composable
fun PermanentNavigationDrawerApp(currentPage: String, currentDestination: String, navigateBack: () -> Unit, navController: NavHostController) {
    PermanentNavigationDrawer(
        drawerContent = {
            val drawerContentModifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(dimensionResource(R.dimen.standard_padding))

            PermanentDrawerSheet(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .background(MaterialTheme.colorScheme.primary),
            ) {
                Box(
                    modifier = drawerContentModifier
                        .fillMaxHeight(),
                ) {
                    NavigationDrawerContent(
                        selectedDestination = navController.currentDestination,
                        onTabPressed = { node: String -> navController.navigate(node) },
                        modifier = drawerContentModifier
                            .wrapContentSize(),
                    )
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                if (currentPage !in noTopAppbarRoutes) {
                    MyTopAppBar(currentpage = currentPage) {
                        if (currentDestination != Destinations.Home.route) {
                            BackButton(onBackPressed = navigateBack)
                        }
                    }
                }
            },
        ) { innerPadding ->
            navComponent(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                navigationType = MoviesAndSeriesNavigationType.PERMANENT_NAVIGATION_DRAWER,
            )
        }
    }
}

/**
 * Composable function for the app structure with Navigation Rail.
 *
 * @param currentPage Current page being displayed.
 * @param currentDestination Current destination being navigated to.
 * @param navigateBack Function to navigate back.
 * @param navController NavHostController used for navigation.
 */
@Composable
fun NavigationRailApp(currentPage: String, currentDestination: String, navigateBack: () -> Unit, navController: NavHostController) {
    Row {
        AnimatedVisibility(visible = true) {
            MoviesAndSeriesNavigationRail(
                selectedDestination = navController.currentDestination,
                onTabPressed = { node: String -> navController.navigate(node) },
            )
        }
        Scaffold(
            topBar = {
                if (currentPage !in noTopAppbarRoutes) {
                    MyTopAppBar(currentpage = currentPage) {
                        if (currentDestination != Destinations.Home.route) {
                            BackButton(onBackPressed = navigateBack)
                        }
                    }
                }
            },
        ) { innerPadding ->
            navComponent(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                navigationType = MoviesAndSeriesNavigationType.NAVIGATION_RAIL,
            )
        }
    }
}
