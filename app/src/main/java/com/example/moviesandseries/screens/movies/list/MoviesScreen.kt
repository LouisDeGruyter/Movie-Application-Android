package com.example.moviesandseries.screens.movies.list

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.moviesandseries.model.movie.MovieIndex
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import android.graphics.Paint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.moviesandseries.network.ApiEndpoints

private const val COLUMN_COUNT = 3
private val GRID_SPACING= 10.dp
@Composable
fun MoviesScreen(movieViewModel: MovieViewModel, onMovieClick: (movieId:Int) -> Unit) {
    var movieUiState: MovieUiState= movieViewModel.movieUiState // if new variables get assigned to movieUiState
    var moviesByPage = movieViewModel.moviePager.collectAsLazyPagingItems()
    when(moviesByPage.loadState.refresh){
        is LoadState.Loading -> Text(text = "Loading")
        is LoadState.Error -> Text(text = "Error")
        else -> {
            displayMovies(movies = moviesByPage, onMovieClick = onMovieClick )

        }
    }
   
}



@Composable
fun displayMovies(movies: LazyPagingItems<MovieIndex>, onMovieClick: (movieId: Int) -> Unit) {
    val lazyGridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(COLUMN_COUNT),
        state = lazyGridState,
        contentPadding = PaddingValues(
            start = GRID_SPACING,
            end = GRID_SPACING,
            top = GRID_SPACING,
            bottom = GRID_SPACING
        ),
        horizontalArrangement = Arrangement.spacedBy(GRID_SPACING,Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(GRID_SPACING,Alignment.CenterVertically),
        content = {
            items(movies.itemCount) { movie ->
                movie?.let {
                    movies[it]?.let { it1 -> MovieItem(movie = it1, onMovieClick = onMovieClick) }
                }
            }

        }
    )

}




@Composable
fun MovieItem(movie: MovieIndex, onMovieClick: (Int) -> Unit) {
    // Customize this composable based on how you want to display each movie item
    ElevatedCard(
        modifier = Modifier
            .clickable(onClick = { onMovieClick(movie.id) }),
        shape = RoundedCornerShape(12.dp),

    ) {
        RatingComposable(rating = movie.voteAverage, modifier = Modifier.zIndex(2f).padding(8.dp))

            val painter= rememberAsyncImagePainter(model = ApiEndpoints.Poster+movie.posterPath,
                error = rememberVectorPainter(image = Icons.Filled.BrokenImage),
                placeholder = rememberVectorPainter(image = Icons.Filled.LocalMovies)
            )
            Image(painter = painter, contentDescription = movie.title, contentScale = ContentScale.FillWidth, modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 3f), alignment = Alignment.Center)
    }
}

@Composable
fun MovieRating(rating: Double) {

}
@Composable
fun RatingComposable(rating: Double,modifier: Modifier = Modifier) {
    var animatedRating by remember { mutableStateOf(rating) }

    LaunchedEffect(key1 = rating){
        animatedRating = rating
    }

    val transition = updateTransition(targetState = animatedRating)
    val color by transition.animateColor(label = "") {
        when {
            it >= 75.0 -> Color.Green
            it >= 50.0 -> {
                Color.Yellow
            }
            it<50.0 -> Color.Red
            else -> Color.Red
        }
    }


    val circleSize = 30.dp
    val progress = (animatedRating / 100.0).toFloat()

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        Canvas(
            modifier = Modifier.size(circleSize)
        ) {
            // Draw inner circle with a black border
            val innerCircleRadius = size.width/2
            val strokeWidth= 8f
            drawCircle(
                color = Color.Black,
                radius = innerCircleRadius,
                center = Offset(size.width / 2, size.height / 2),
                style = androidx.compose.ui.graphics.drawscope.Fill
            )
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360f * progress,
                useCenter = false,
                style = Stroke(strokeWidth,cap = StrokeCap.Round),
                topLeft = Offset(strokeWidth, strokeWidth),
                size = Size((innerCircleRadius-strokeWidth) * 2, (innerCircleRadius-strokeWidth) * 2),
            )
            drawArc(
                color = color.copy(alpha = 0.5f), // Adjust the alpha for transparency
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(strokeWidth,cap = StrokeCap.Round),
                topLeft = Offset(strokeWidth, strokeWidth),
                size = Size((innerCircleRadius-strokeWidth) * 2, (innerCircleRadius-strokeWidth) * 2),

            )

            // Draw the actual inner circle (to cover the black border)


            // Draw text in the center
            drawIntoCanvas {
                val text = (animatedRating.toInt()).toString()
                val textSize = 14.dp.toPx()
                val paint = Paint()
                paint.textSize = textSize
                paint.color = Color.White.toArgb()
                val xOffset = (size.width - paint.measureText(text)) / 2
                val yOffset = (size.height + textSize-10) / 2
                it.nativeCanvas.drawText(text, xOffset, yOffset, paint)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRatingComposable() {
    RatingComposable(rating = 80.0)
}

