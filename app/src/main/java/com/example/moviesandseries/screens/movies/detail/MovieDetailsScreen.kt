package com.example.moviesandseries.screens.movies.detail

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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviesandseries.screens.components.detail.ActorList
import com.example.moviesandseries.screens.components.detail.Backdrop
import com.example.moviesandseries.screens.components.detail.DisplayCollection
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

/**
 * Composable function representing the MovieDetailsScreen.
 *
 * @param movieId The ID of the movie.
 * @param movieDetailViewModel The ViewModel for the MovieDetailsScreen.
 * @param backButton A composable function for rendering the back button.
 * @param onMovieClick Callback function for handling movie clicks.
 * @param onSeriesClick Callback function for handling series clicks.
 * @param navigationType The type of navigation used in the app.
 */
@Composable
fun MovieDetailsScreen(movieId: String?, movieDetailViewModel: MovieDetailViewModel = viewModel(factory = MovieDetailViewModel.Factory), backButton: @Composable (Modifier) -> Unit, onMovieClick: (movieId: Int) -> Unit, onSeriesClick: (seriesId: Int) -> Unit, navigationType: MoviesAndSeriesNavigationType) {
    when (val movieDetailUiState = movieDetailViewModel.movieDetailApiState) {
        is MovieDetailApiState.Success -> {
            val movieDetailListState = movieDetailViewModel.uiListMovieDetailState.collectAsState().value
            DisplayMovieDetail(backButton = backButton, onMovieClick = onMovieClick, onSeriesClick = onSeriesClick, movieDetailListState = movieDetailListState, navigationType = navigationType, onFavoriteClick = { movieDetailViewModel.updateFavorite() })
        }
        is MovieDetailApiState.Loading -> {
            LoadingAnimation()
        }
        is MovieDetailApiState.Error -> {
            ErrorText(message = movieDetailUiState.message)
        }
    }

    // Trigger the getMovieDetail function when the screen is initially displayed
    LaunchedEffect(key1 = movieId) {
        if (movieId != null) {
            movieDetailViewModel.getMovieDetail(movieId.toInt())
        }
    }
}

/**
 * Composable function to display movie details.
 *
 * @param backButton A composable function for rendering the back button.
 * @param onMovieClick Callback function for handling movie clicks.
 * @param onSeriesClick Callback function for handling series clicks.
 * @param movieDetailListState The state representing movie details.
 * @param navigationType The type of navigation used in the app.
 * @param onFavoriteClick Callback function for handling favorite button clicks.
 */
@Composable
fun DisplayMovieDetail(backButton: @Composable (modifier: Modifier) -> Unit, onMovieClick: (movieId: Int) -> Unit, onSeriesClick: (seriesId: Int) -> Unit, movieDetailListState: MovieDetailListState, navigationType: MoviesAndSeriesNavigationType, onFavoriteClick: () -> Unit) {
    var fadeIn by remember { mutableStateOf(false) }
    var showImageCarousel by remember { mutableStateOf(false) }
    var scrollToTop by remember { mutableStateOf(0) }
    var fullscreen by remember { mutableStateOf(false) }
    val movie = movieDetailListState.movieDetail
    val images = movieDetailListState.images
    val credits = movieDetailListState.credits
    val movieVideos = movieDetailListState.videos
    val collectionDetail = movieDetailListState.collection
    val recommendedMedia = movieDetailListState.recommendedMedia.collectAsLazyPagingItems()
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

    LaunchedEffect(key1 = movie) {
        fadeIn = true
        if (navigationType != MoviesAndSeriesNavigationType.BOTTOM_NAVIGATION) {
            verticalState.animateScrollTo(componentSizes.movieBackdropHeight, animationSpec = tween(1000, easing = LinearEasing))
        }
    }
    LaunchedEffect(scrollToTop) {
        if (showImageCarousel) {
            verticalState.animateScrollTo(0, animationSpec = tween(500, easing = LinearEasing))
        }
    }
    if (fullscreen) {
        YoutubeScreen(videoId = "dQw4w9WgXcQ", modifier = Modifier.fillMaxHeight(), onFullscreen = { fullscreen = false })
    } else {
        ConstraintLayout(modifier = Modifier.clickable(indication = null, interactionSource = interactionSource) { showImageCarousel = false }.fillMaxSize().verticalScroll(verticalState).padding(0.dp, 0.dp, 0.dp, 50.dp).testTag("MovieDetailScreen")) {
            val (backdrop, mediaCard, backButtonRef, movieContent, favorite) = createRefs()

            Backdrop(images = images, title = movie.title, onCarouselClick = { showImageCarousel = true; scrollToTop++ }, fadeIn = fadeIn, modifier = Modifier.constrainAs(backdrop) { top.linkTo(parent.top); absoluteLeft.linkTo(parent.absoluteLeft); absoluteRight.linkTo(parent.absoluteRight) }.height(componentSizes.movieBackdropHeight.dp))
            // center mediacard on the bottom of the backdrop
            MediaCard(title = movie.title, imagePath = movie.posterPath, rating = movie.voteAverage, modifier = Modifier.fillMaxWidth(componentSizes.moviePosterWidth).zIndex(zIndexPoster).constrainAs(mediaCard) { top.linkTo(backdrop.bottom); bottom.linkTo(backdrop.bottom); absoluteLeft.linkTo(parent.absoluteLeft); absoluteRight.linkTo(parent.absoluteRight) })

            Box(modifier = Modifier.constrainAs(backButtonRef) { absoluteRight.linkTo(mediaCard.absoluteLeft); absoluteLeft.linkTo(parent.absoluteLeft); top.linkTo(backdrop.bottom, margin = 15.dp) }) {
                backButton(Modifier.height(40.dp))
            }
            Box(modifier = Modifier.constrainAs(favorite) { absoluteRight.linkTo(parent.absoluteRight); absoluteLeft.linkTo(mediaCard.absoluteRight); top.linkTo(backdrop.bottom, margin = 15.dp) }) {
                FavoriteButton(isFavorite = movie.isFavorite, onFavoriteClick = onFavoriteClick, modifier = Modifier.height(40.dp))
            }

            Column(modifier = Modifier.constrainAs(movieContent) { top.linkTo(mediaCard.bottom); absoluteLeft.linkTo(parent.absoluteLeft); absoluteRight.linkTo(parent.absoluteRight) }, verticalArrangement = Arrangement.spacedBy(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                TitleHeader(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    title = movie.title,
                    releaseDate = movie.releaseDate,
                    runtime = movie.runtime,
                )
                Genres(genres = movie.genres.map { it.name }, modifier = Modifier.fillMaxWidth(0.9f))
                ExpandableDescription(catchPhrase = movie.tagline, description = movie.overview, modifier = Modifier.fillMaxWidth(0.9f))
                ActorList(actors = credits.cast)
                if (collectionDetail != null) {
                    DisplayCollection(collection = collectionDetail, onMediaClick = onMovieClick, currentMovieId = movie.id)
                }
                DisplayVideos(videos = movieVideos.results, onFullScreen = { fullscreen = true })
                DisplayRecommended(recommendedMedia = recommendedMedia, onMovieClick = onMovieClick, onSeriesClick = onSeriesClick)
                ProductionCompanies(productionCompanies = movie.productionCompanies)
            }
        } }
}

/**
 * Composable function for rendering a loading animation.
 */
@Composable
fun LoadingAnimation() {
    CircularProgressIndicator(modifier = Modifier.fillMaxSize(), color = Color.Red)
}

/**
 * Composable function for rendering an error message.
 *
 * @param message The error message to display.
 */
@Composable
fun ErrorText(message: String) {
    // Placeholder for error message
    Text(text = message, color = Color.Red, modifier = Modifier.fillMaxSize())
}
