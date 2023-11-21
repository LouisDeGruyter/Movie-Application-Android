package com.example.moviesandseries.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviesandseries.screens.components.loading.LoadingMediaRow
import com.example.moviesandseries.screens.components.movies.LazyMoviesRow
import com.example.moviesandseries.screens.components.movies.LazySeriesRow
private val ROW_HEIGHT = 240.dp
private val ROW_PADDING = 12.dp

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onMovieClick: (movieId: Int) -> Unit,
    onSeriesClick: (seriesId: Int) -> Unit,
) {
    // Getting lists using pagers
    // Movie lists
    val moviesInTheater = homeViewModel.moviesInTheaterPager.collectAsLazyPagingItems()
    val popularMovies = homeViewModel.popularMoviesPagingSource.collectAsLazyPagingItems()
    val topRatedMovies = homeViewModel.topRatedMoviesPagingSource.collectAsLazyPagingItems()
    val upcomingMovies = homeViewModel.upcomingMoviesPagingSource.collectAsLazyPagingItems()
    // Series lists
    val popularSeries = homeViewModel.popularSeriesPagingSource.collectAsLazyPagingItems()
    val topRatedSeries = homeViewModel.topRatedSeriesPagingSource.collectAsLazyPagingItems()
    val onTheAirSeries = homeViewModel.onTheAirSeriesPagingSource.collectAsLazyPagingItems()
    val airingTodaySeries = homeViewModel.airingTodaySeriesPagingSource.collectAsLazyPagingItems()

    // Displaying
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(ROW_PADDING),
        content = {
            item { HomeScreenSectionMovie(title = "Movies in Theater", onMovieClick = onMovieClick, movies = moviesInTheater) }
            item { HomeScreenSectionSeries(title = "Series Airing Today", onSeriesClick = onSeriesClick, series = airingTodaySeries) }

            item { HomeScreenSectionMovie(title = "Popular Movies", onMovieClick = onMovieClick, movies = popularMovies) }
            item { HomeScreenSectionSeries(title = "Popular Series", onSeriesClick = onSeriesClick, series = popularSeries) }

            item { HomeScreenSectionMovie(title = "Top Rated Movies", onMovieClick = onMovieClick, movies = topRatedMovies) }
            item { HomeScreenSectionSeries(title = "Top Rated Series", onSeriesClick = onSeriesClick, series = topRatedSeries) }

            item { HomeScreenSectionMovie(title = "Upcoming Movies", onMovieClick = onMovieClick, movies = upcomingMovies) }
            item { HomeScreenSectionSeries(title = "On the Air Series", onSeriesClick = onSeriesClick, series = onTheAirSeries) }
        },
    )
}

@Composable
fun HomeScreenSectionMovie(title: String, onMovieClick: (movieId: Int) -> Unit, movies: LazyPagingItems<MovieIndex>) {
    when (movies.loadState.refresh) {
        is LoadState.Loading -> LoadingHomeScreenSection(mediaType = "Movies")
        is LoadState.Error -> Text(text = "Error")
        else -> {
            Column(modifier = Modifier.padding(start = ROW_PADDING), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = title, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                LazyMoviesRow(
                    movies = movies,
                    onMovieClick = onMovieClick,
                    modifier = Modifier.height(
                        ROW_HEIGHT,
                    ),
                )
            }
        }
    }
}

@Composable
fun HomeScreenSectionSeries(title: String, onSeriesClick: (seriesId: Int) -> Unit, series: LazyPagingItems<SeriesIndex>) {
    when (series.loadState.refresh) {
        is LoadState.Loading -> LoadingHomeScreenSection(mediaType = "Series")
        is LoadState.Error -> Text(text = "Error")
        else -> {
            Column(modifier = Modifier.padding(start = ROW_PADDING), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = title, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                LazySeriesRow(series = series, onSeriesClick = onSeriesClick, modifier = Modifier.height(ROW_HEIGHT))
            }
        }
    }
}

@Composable
fun LoadingHomeScreenSection(mediaType: String) {
    Column(modifier = Modifier.padding(start = ROW_PADDING), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = mediaType, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        LoadingMediaRow(3, modifier = Modifier.height(ROW_HEIGHT))
    }
}
