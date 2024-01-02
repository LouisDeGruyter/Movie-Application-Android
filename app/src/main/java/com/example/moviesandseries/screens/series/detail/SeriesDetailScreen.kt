package com.example.moviesandseries.screens.series.detail

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviesandseries.screens.components.detail.ActorList
import com.example.moviesandseries.screens.components.detail.Backdrop
import com.example.moviesandseries.screens.components.detail.DisplayRecommended
import com.example.moviesandseries.screens.components.detail.DisplayVideos
import com.example.moviesandseries.screens.components.detail.ExpandableDescription
import com.example.moviesandseries.screens.components.detail.FavoriteButton
import com.example.moviesandseries.screens.components.detail.Genres
import com.example.moviesandseries.screens.components.detail.ProductionCompanies
import com.example.moviesandseries.screens.components.detail.TitleHeader
import com.example.moviesandseries.screens.components.detail.YoutubeScreen
import com.example.moviesandseries.screens.components.index.mediaCard.MediaCard
import com.example.moviesandseries.util.ComponentSizes
import com.example.moviesandseries.util.MoviesAndSeriesNavigationType

@Composable
fun SeriesDetailScreen(seriesId: String?, seriesDetailViewModel: SeriesDetailViewModel = viewModel(factory = SeriesDetailViewModel.Factory), backButton: @Composable (Modifier) -> Unit, onMovieClick: (movieId: Int) -> Unit, onSeriesClick: (seriesId: Int) -> Unit, navigationType: MoviesAndSeriesNavigationType) {
    when (val seriesDetailUiState = seriesDetailViewModel.seriesDetailApiState) {
        is SeriesDetailApiState.Success -> {
            val seriesDetailListState = seriesDetailViewModel.uiListSeriesDetailState.collectAsState().value
            DisplaySeriesDetail(backButton = backButton, onMovieClick = onMovieClick, onSeriesClick = onSeriesClick, seriesDetailListState = seriesDetailListState, navigationType = navigationType, onFavoriteClick = { seriesDetailViewModel.updateFavorite() }, onSeasonUpdate = { seasonNumber: Int -> seriesDetailViewModel.getSeriesDetail(seasonNumber) })
        }
        is SeriesDetailApiState.Loading -> {
            Text(text = "Loading")
        }
        is SeriesDetailApiState.Error -> {
            Text(text = seriesDetailUiState.message)
        }
    }
    LaunchedEffect(key1 = seriesId) {
        if (seriesId != null) {
            seriesDetailViewModel.getSeriesDetail(seriesId.toInt())
        }
    }
}

@Composable
fun DisplaySeriesDetail(
    backButton: @Composable (Modifier) -> Unit,
    onMovieClick: (movieId: Int) -> Unit,
    onSeriesClick: (seriesId: Int) -> Unit,
    seriesDetailListState: SeriesDetailListState,
    navigationType: MoviesAndSeriesNavigationType,
    onFavoriteClick: () -> Unit,
    onSeasonUpdate: (Int) -> Unit,
) {
    var fadeIn by remember { mutableStateOf(false) }
    var showImageCarousel by remember { mutableStateOf(false) }
    var scrollToTop by remember { mutableStateOf(0) }
    var fullscreen by remember { mutableStateOf(false) }
    val series = seriesDetailListState.seriesDetail
    val images = seriesDetailListState.images
    val credits = seriesDetailListState.credits
    val videos = seriesDetailListState.videos
    val recommendedMedia = seriesDetailListState.recommendedMedia.collectAsLazyPagingItems()
    Log.d("SeriesDetailScreen", "recommendedMedia: $recommendedMedia")
    val seasonDetail = seriesDetailListState.seasonDetail
    val verticalState = rememberScrollState()
    val interactionSource = remember { MutableInteractionSource() }
    val zIndexPoster by remember {
        derivedStateOf {
            if (showImageCarousel) -1f else 2f
        }
    }
    val componentSizes =
        when (navigationType) {
            MoviesAndSeriesNavigationType.BOTTOM_NAVIGATION -> {
                ComponentSizes(moviePosterWidth = 0.5f, movieBackdropHeight = 300)
            }

            MoviesAndSeriesNavigationType.NAVIGATION_RAIL -> {
                ComponentSizes(moviePosterWidth = 0.2f, movieBackdropHeight = 300)
            }

            MoviesAndSeriesNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
                ComponentSizes(moviePosterWidth = 0.3f, movieBackdropHeight = 600)
            }
        }

    LaunchedEffect(key1 = series) {
        fadeIn = true
        if (navigationType != MoviesAndSeriesNavigationType.BOTTOM_NAVIGATION) {
            verticalState.animateScrollTo(
                componentSizes.movieBackdropHeight,
                animationSpec = tween(1000, easing = LinearEasing),
            )
        }
    }
    LaunchedEffect(scrollToTop) {
        if (showImageCarousel) {
            verticalState.animateScrollTo(0, animationSpec = tween(500, easing = LinearEasing))
        }
    }
    if (fullscreen) {
        YoutubeScreen(
            videoId = "dQw4w9WgXcQ",
            modifier = Modifier.fillMaxHeight(),
            onFullscreen = { fullscreen = false },
        )
    } else {
        ConstraintLayout(modifier = Modifier.clickable(indication = null, interactionSource = interactionSource) { showImageCarousel = false }.fillMaxSize().verticalScroll(verticalState).padding(0.dp, 0.dp, 0.dp, 50.dp).testTag("SeriesDetailScreen")) {
            val (backdrop, mediaCard, backButtonRef, seriesContent, favorite) = createRefs()

            Backdrop(images = images, title = series.name, onCarouselClick = { showImageCarousel = true; scrollToTop++ }, fadeIn = fadeIn, modifier = Modifier.constrainAs(backdrop) { top.linkTo(parent.top); absoluteLeft.linkTo(parent.absoluteLeft); absoluteRight.linkTo(parent.absoluteRight) }.height(componentSizes.movieBackdropHeight.dp))
            // center mediacard on the bottom of the backdrop
            MediaCard(title = series.name, imagePath = series.posterPath, rating = series.voteAverage, modifier = Modifier.fillMaxWidth(componentSizes.moviePosterWidth).zIndex(zIndexPoster).constrainAs(mediaCard) { top.linkTo(backdrop.bottom); bottom.linkTo(backdrop.bottom); absoluteLeft.linkTo(parent.absoluteLeft); absoluteRight.linkTo(parent.absoluteRight) })

            Box(modifier = Modifier.constrainAs(backButtonRef) { absoluteRight.linkTo(mediaCard.absoluteLeft); absoluteLeft.linkTo(parent.absoluteLeft); top.linkTo(backdrop.bottom, margin = 15.dp) }) {
                backButton(Modifier.height(40.dp))
            }
            Box(modifier = Modifier.constrainAs(favorite) { absoluteRight.linkTo(parent.absoluteRight); absoluteLeft.linkTo(mediaCard.absoluteRight); top.linkTo(backdrop.bottom, margin = 15.dp) }) {
                FavoriteButton(isFavorite = series.isFavorite, onFavoriteClick = onFavoriteClick, modifier = Modifier.height(40.dp))
            }
            Column(modifier = Modifier.constrainAs(seriesContent) { top.linkTo(mediaCard.bottom); absoluteLeft.linkTo(parent.absoluteLeft); absoluteRight.linkTo(parent.absoluteRight) }, verticalArrangement = Arrangement.spacedBy(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                TitleHeader(title = series.name, releaseDate = series.firstAirDate, numberOfSeasons = series.numberOfSeasons, modifier = Modifier.fillMaxWidth(0.9f))
                Genres(genres = series.genres.map { it.name }, modifier = Modifier.fillMaxWidth(0.9f))
                ExpandableDescription(catchPhrase = series.tagline, description = series.overview, modifier = Modifier.fillMaxWidth(0.9f))
                ActorList(actors = credits.cast)
                /**if (series.numberOfSeasons > 0) {
                 DisplaySeasons(seasons = series.seasons, seasonDetail = seasonDetail, onSeasonUpdate = onSeasonUpdate)
                 }**/
                DisplayVideos(videos = videos.results, onFullScreen = { fullscreen = true })
                DisplayRecommended(recommendedMedia = recommendedMedia, onMovieClick = onMovieClick, onSeriesClick = onSeriesClick)
                ProductionCompanies(productionCompanies = series.productionCompanies)
            }
        }
    }
}
/**
@Composable
fun DisplaySeasons(seasons: List<Season>, seasonDetail: Season, onSeasonUpdate: (Int) -> Unit) {
}
 **/
