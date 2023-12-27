package com.example.moviesandseries.screens.components.detail.movie

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.moviesandseries.R
import com.example.moviesandseries.domain.images.ImagesContainer
import com.example.moviesandseries.domain.movie.MovieDetail
import com.example.moviesandseries.screens.components.detail.BackgroundImage
import com.example.moviesandseries.screens.components.index.mediaCard.MediaCard

@Composable
fun MovieDetailComposable(
    movie: MovieDetail,
    backButton: @Composable (modifier: Modifier) -> Unit,
    images: ImagesContainer,
) {
    var fadeIn by remember { mutableStateOf(false) }
    var showImageCarousel by remember { mutableStateOf(false) }
    val zIndexPoster by remember {
        derivedStateOf {
            if (showImageCarousel) -1f else 2f
        }
    }
    LaunchedEffect(key1 = movie) {
        fadeIn = true
    }

    ConstraintLayout(modifier = Modifier.clickable { showImageCarousel = false }.fillMaxHeight()) {
        val (backdrop, mediaCard, backButtonRef, title) = createRefs()
        Card(
            modifier = Modifier.fillMaxHeight(0.4f).constrainAs(backdrop) { top.linkTo(parent.top); absoluteLeft.linkTo(parent.absoluteLeft); absoluteRight.linkTo(parent.absoluteRight) },
            shape = RectangleShape,
        ) {
            AnimatedVisibility(visible = fadeIn, enter = fadeIn(animationSpec = tween(1500, easing = LinearEasing))) {
                BackdropCarousel(images = images, title = movie.title, onCarouselClick = { showImageCarousel = true })
            }
        }

        // center mediacard on the bottom of the backdrop
        MediaCard(title = movie.title, imagePath = movie.posterPath, rating = movie.voteAverage, modifier = Modifier.fillMaxWidth(0.5f).zIndex(zIndexPoster).constrainAs(mediaCard) { top.linkTo(backdrop.bottom); bottom.linkTo(backdrop.bottom); absoluteLeft.linkTo(parent.absoluteLeft); absoluteRight.linkTo(parent.absoluteRight) })
        Box(modifier = Modifier.constrainAs(backButtonRef) { absoluteRight.linkTo(mediaCard.absoluteLeft); absoluteLeft.linkTo(parent.absoluteLeft); top.linkTo(backdrop.bottom, margin = 15.dp) }) {
            backButton(Modifier.height(40.dp))
        }
        Column(modifier = Modifier.constrainAs(title) { top.linkTo(mediaCard.bottom); absoluteLeft.linkTo(parent.absoluteLeft); absoluteRight.linkTo(parent.absoluteRight) }.fillMaxWidth(0.9f)) {
            Text(
                text = movie.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),

            )
            val normalTextStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = movie.releaseDate.substring(0, 4) + " | ",
                    style = normalTextStyle,
                )
                Text(
                    text = movie.runtime.toString() + " min",
                    style = normalTextStyle,
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BackdropCarousel(images: ImagesContainer, title: String, onCarouselClick: (Int) -> Unit) {
    val backdropImages = images.backdrops
    val pagerState = rememberPagerState(pageCount = { backdropImages.size })

    Box(modifier = Modifier.fillMaxSize().clickable { onCarouselClick(pagerState.currentPage) }) {
        HorizontalPager(
            state = pagerState,
            key = { it },
        ) {
                index ->
            BackgroundImage(imagePath = backdropImages[index].filePath, title = title)
        }
    }
    Box(modifier = Modifier.fillMaxSize().padding(8.dp), contentAlignment = Alignment.TopEnd) {
        Box(modifier = Modifier.clip(RoundedCornerShape(30)).background(color = Color.Black.copy(alpha = 0.5f))) {
            Text(
                text = "${pagerState.currentPage + 1} / ${backdropImages.size}",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(
                    PaddingValues(4.dp, 1.dp),
                ),
            )
//
        }
    }
}
