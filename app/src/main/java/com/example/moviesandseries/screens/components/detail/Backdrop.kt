package com.example.moviesandseries.screens.components.detail

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesandseries.domain.images.ImagesContainer

@Composable
fun Backdrop(modifier: Modifier = Modifier, images: ImagesContainer, title: String, onCarouselClick: () -> Unit, fadeIn: Boolean = true) {
    Card(
        modifier = modifier,
        shape = RectangleShape,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            androidx.compose.animation.AnimatedVisibility(visible = fadeIn, enter = fadeIn(animationSpec = tween(1500, easing = LinearEasing))) {
                BackdropCarousel(images = images, title = title, onCarouselClick = { onCarouselClick() })
            }
            val fadeColor = if (isSystemInDarkTheme()) {
                Color.Black
            } else {
                Color.White
            }
            Spacer(modifier = Modifier.fillMaxSize().background(brush = Brush.verticalGradient(0.7f to Color.Transparent, 1f to fadeColor)))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BackdropCarousel(images: ImagesContainer, title: String, onCarouselClick: (Int) -> Unit) {
    val backdropImages = images.backdrops
    val pagerState = rememberPagerState(pageCount = { backdropImages.size })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onCarouselClick(pagerState.currentPage) },
    ) {
        HorizontalPager(
            state = pagerState,
            key = { it },
        ) { index ->
            BackgroundImage(imagePath = backdropImages[index].filePath, title = title)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.TopEnd,
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(30))
                .background(color = Color.Black.copy(alpha = 0.5f)),
        ) {
            Text(
                text = "${pagerState.currentPage + 1} / ${backdropImages.size}",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(
                    PaddingValues(4.dp, 1.dp),
                ),
            )
        }
    }
}
