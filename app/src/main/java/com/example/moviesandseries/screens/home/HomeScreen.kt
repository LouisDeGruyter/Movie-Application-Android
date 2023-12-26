package com.example.moviesandseries.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviesandseries.R
import com.example.moviesandseries.domain.movie.MovieIndex
import com.example.moviesandseries.domain.series.SeriesIndex
import com.example.moviesandseries.screens.components.loading.LoadingMediaRow
import com.example.moviesandseries.screens.components.movies.LazyMoviesRow
import com.example.moviesandseries.screens.components.movies.LazySeriesRow

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory), onMovieClick: (movieId: Int) -> Unit, onSeriesClick: (seriesId: Int) -> Unit) {
    val ROW_HEIGHT = dimensionResource(id = R.dimen.row_height)
    val rowPadding = dimensionResource(id = R.dimen.row_padding)
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
        verticalArrangement = Arrangement.spacedBy(rowPadding),
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
    val rowHeight = dimensionResource(id = R.dimen.row_height)
    val rowPadding = dimensionResource(id = R.dimen.row_padding)
    when (movies.loadState.refresh) {
        is LoadState.Loading -> LoadingHomeScreenSection(mediaType = "Movies")
        is LoadState.Error -> Text(text = "Error")
        else -> {
            Column(modifier = Modifier.padding(start = rowPadding), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = title, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                LazyMoviesRow(
                    movies = movies,
                    onMovieClick = onMovieClick,
                    modifier = Modifier.height(
                        rowHeight,
                    ),
                )
            }
        }
    }
}

@Composable
fun HomeScreenSectionSeries(title: String, onSeriesClick: (seriesId: Int) -> Unit, series: LazyPagingItems<SeriesIndex>) {
    val rowHeight = dimensionResource(id = R.dimen.row_height)
    val rowPadding = dimensionResource(id = R.dimen.row_padding)
    when (series.loadState.refresh) {
        is LoadState.Loading -> LoadingHomeScreenSection(mediaType = "Series")
        is LoadState.Error -> Text(text = "Error")
        else -> {
            Column(modifier = Modifier.padding(start = rowPadding), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = title, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                LazySeriesRow(series = series, onSeriesClick = onSeriesClick, modifier = Modifier.height(rowHeight))
            }
        }
    }
}

@Composable
fun LoadingHomeScreenSection(mediaType: String) {
    val rowPadding = dimensionResource(id = R.dimen.row_padding)
    val rowHeight = dimensionResource(id = R.dimen.row_height)
    Column(modifier = Modifier.padding(start = rowPadding), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = mediaType, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        LoadingMediaRow(3, modifier = Modifier.height(rowHeight))
    }
}
