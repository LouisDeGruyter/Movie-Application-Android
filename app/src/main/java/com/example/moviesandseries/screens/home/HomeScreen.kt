package com.example.moviesandseries.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviesandseries.R
import com.example.moviesandseries.domain.MediaIndex
import com.example.moviesandseries.screens.components.index.LazyPagingMediaRow
import com.example.moviesandseries.screens.components.loading.LoadingMediaRow

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory), onMovieClick: (movieId: Int) -> Unit, onSeriesClick: (seriesId: Int) -> Unit) {
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
            item { HomeScreenSection(title = "Movies in Theater", onMediaClick = onMovieClick, media = moviesInTheater, mediaType = "Movies") }
            item { HomeScreenSection(title = "Series Airing Today", onMediaClick = onSeriesClick, media = airingTodaySeries, mediaType = "Series") }

            item { HomeScreenSection(title = "Popular Movies", onMediaClick = onMovieClick, media = popularMovies, mediaType = "Movies") }
            item { HomeScreenSection(title = "Popular Series", onMediaClick = onSeriesClick, media = popularSeries, mediaType = "Series") }

            item { HomeScreenSection(title = "Top Rated Movies", onMediaClick = onMovieClick, media = topRatedMovies, mediaType = "Movies") }
            item { HomeScreenSection(title = "Top Rated Series", onMediaClick = onSeriesClick, media = topRatedSeries, mediaType = "Series") }

            item { HomeScreenSection(title = "Upcoming Movies", onMediaClick = onMovieClick, media = upcomingMovies, mediaType = "Movies") }
            item { HomeScreenSection(title = "On the Air Series", onMediaClick = onSeriesClick, media = onTheAirSeries, mediaType = "Series") }
        },
    )
}

@Composable
fun HomeScreenSection(title: String, onMediaClick: (mediaId: Int) -> Unit, media: LazyPagingItems<MediaIndex>, mediaType: String) {
    val rowHeight = dimensionResource(id = R.dimen.row_height)
    val rowPadding = dimensionResource(id = R.dimen.row_padding)
    when (media.loadState.refresh) {
        is LoadState.Loading -> LoadingHomeScreenSection(header = mediaType)
        is LoadState.Error -> Text(text = "Error")
        else -> {
            Column(modifier = Modifier.padding(start = rowPadding), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = title, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                LazyPagingMediaRow(media = media, onMediaClick = onMediaClick, modifier = Modifier.height(rowHeight).testTag(mediaType), mediaType = mediaType)
            }
        }
    }
}

@Composable
fun LoadingHomeScreenSection(header: String) {
    val rowPadding = dimensionResource(id = R.dimen.row_padding)
    val rowHeight = dimensionResource(id = R.dimen.row_height)
    Column(modifier = Modifier.padding(start = rowPadding), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = header, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        LoadingMediaRow(3, modifier = Modifier.height(rowHeight))
    }
}
