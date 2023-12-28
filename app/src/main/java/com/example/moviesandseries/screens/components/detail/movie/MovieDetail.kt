package com.example.moviesandseries.screens.components.detail.movie

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Cases
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.moviesandseries.R
import com.example.moviesandseries.domain.ProductionCompany
import com.example.moviesandseries.domain.credits.Credit
import com.example.moviesandseries.domain.credits.CreditsContainer
import com.example.moviesandseries.domain.images.ImagesContainer
import com.example.moviesandseries.domain.movie.MovieDetail
import com.example.moviesandseries.network.ApiEndpoints
import com.example.moviesandseries.screens.components.TwoByThreeAspectRatioImage
import com.example.moviesandseries.screens.components.detail.BackgroundImage
import com.example.moviesandseries.screens.components.index.mediaCard.MediaCard

val normalTextStyleCentered = TextStyle(
    fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    textAlign = TextAlign.Center,
)

@Composable
fun MovieDetailComposable(
    movie: MovieDetail,
    backButton: @Composable (modifier: Modifier) -> Unit,
    images: ImagesContainer,
    credits: CreditsContainer,
) {
    var fadeIn by remember { mutableStateOf(false) }
    var showImageCarousel by remember { mutableStateOf(false) }
    var scrollToTop by remember { mutableStateOf(0) }
    val zIndexPoster by remember {
        derivedStateOf {
            if (showImageCarousel) -1f else 2f
        }
    }
    val verticalState = rememberScrollState()
    LaunchedEffect(key1 = movie) {
        fadeIn = true
    }
    LaunchedEffect(scrollToTop) {
        if (showImageCarousel) {
            verticalState.animateScrollTo(0, animationSpec = tween(500, easing = LinearEasing))
        }
    }
    val interactionSource = remember { MutableInteractionSource() }
    ConstraintLayout(
        modifier = Modifier.clickable(indication = null, interactionSource = interactionSource) { showImageCarousel = false }.fillMaxSize().verticalScroll(verticalState).padding(0.dp, 0.dp, 0.dp, 50.dp),
    ) {
        val (backdrop, mediaCard, backButtonRef, movieContent) = createRefs()
        Card(
            modifier = Modifier.constrainAs(backdrop) { top.linkTo(parent.top); absoluteLeft.linkTo(parent.absoluteLeft); absoluteRight.linkTo(parent.absoluteRight); }.height(300.dp),
            shape = RectangleShape,
        ) {
            AnimatedVisibility(visible = fadeIn, enter = fadeIn(animationSpec = tween(1500, easing = LinearEasing))) {
                BackdropCarousel(images = images, title = movie.title, onCarouselClick = { showImageCarousel = true; scrollToTop++ })
            }
        }

        // center mediacard on the bottom of the backdrop
        MediaCard(title = movie.title, imagePath = movie.posterPath, rating = movie.voteAverage, modifier = Modifier.fillMaxWidth(0.5f).zIndex(zIndexPoster).constrainAs(mediaCard) { top.linkTo(backdrop.bottom); bottom.linkTo(backdrop.bottom); absoluteLeft.linkTo(parent.absoluteLeft); absoluteRight.linkTo(parent.absoluteRight) })
        Box(modifier = Modifier.constrainAs(backButtonRef) { absoluteRight.linkTo(mediaCard.absoluteLeft); absoluteLeft.linkTo(parent.absoluteLeft); top.linkTo(backdrop.bottom, margin = 15.dp) }) {
            backButton(Modifier.height(40.dp))
        }

        Column(modifier = Modifier.constrainAs(movieContent) { top.linkTo(mediaCard.bottom); absoluteLeft.linkTo(parent.absoluteLeft); absoluteRight.linkTo(parent.absoluteRight) }, verticalArrangement = Arrangement.spacedBy(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            TitleHeader(title = movie.title, releaseDate = movie.releaseDate, runtime = movie.runtime, modifier = Modifier.fillMaxWidth(0.9f))
            Genres(genres = movie.genres.map { it?.name ?: "" }, modifier = Modifier.fillMaxWidth(0.9f))
            ExpandableDescription(catchPhrase = movie.tagline, description = movie.overview, modifier = Modifier.fillMaxWidth(0.9f))
            ActorList(actors = credits.cast)
            ProductionCompanies(productionCompanies = movie.productionCompanies)
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
        }
    }
}

@Composable
fun ExpandableDescription(modifier: Modifier = Modifier, catchPhrase: String, description: String) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        Text(
            text = catchPhrase,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
            ),
        )
        Text(text = description, maxLines = if (expanded) Int.MAX_VALUE else 3, overflow = TextOverflow.Ellipsis, modifier = Modifier.animateContentSize(animationSpec = tween(500)).clickable { expanded = !expanded })
    }
}

@Composable
fun TitleHeader(modifier: Modifier = Modifier, title: String, releaseDate: String, runtime: Int) {
    Column(modifier = modifier.padding(PaddingValues(0.dp, 8.dp)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),

        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = releaseDate.substring(0, 4) + " | ",
                style = normalTextStyleCentered,
            )
            Text(
                text = "$runtime min",
                style = normalTextStyleCentered,
            )
        }
    }
}

@Composable
fun Genres(modifier: Modifier = Modifier, genres: List<String>) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        genres.forEach {
                genre ->
            if (genre.isEmpty()) return
            Text(
                text = genre,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.border(2.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(50)).padding(8.dp, 3.dp),
            )
            if (genre != genres.last()) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun ProductionCompanies(modifier: Modifier = Modifier, productionCompanies: List<ProductionCompany?>) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.End) {
        Text(
            text = "Production Companies",
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
            modifier = modifier.fillMaxWidth(0.95f),
        )
        LazyRow(modifier = modifier.fillMaxWidth()) {
            items(productionCompanies.size) {
                productionCompanies[it]?.let { it1 ->
                    Spacer(modifier = Modifier.width(16.dp))
                    ProductionCompanyCard(
                        productionCompany = it1,
                        modifier = Modifier.width(140.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun ProductionCompanyCard(modifier: Modifier = Modifier, productionCompany: ProductionCompany) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        ElevatedCard(modifier = Modifier.fillMaxHeight(0.8f).aspectRatio(1f), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(12.dp)) {
            ProductionCompanyImage(imagePath = productionCompany.logoPath ?: "", name = productionCompany.name)
        }
        Text(
            text = productionCompany.name,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            ),
            maxLines = if (expanded) Int.MAX_VALUE else 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.animateContentSize(animationSpec = tween(500)).clickable { expanded = !expanded }.padding(0.dp, 8.dp, 0.dp, 0.dp),
        )
    }
}

@Composable
fun ProductionCompanyImage(imagePath: String, name: String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(ApiEndpoints.Poster + imagePath).crossfade(500).build(),
        error = rememberVectorPainter(image = Icons.Filled.BrokenImage),
        placeholder = rememberVectorPainter(image = Icons.Filled.Cases),
    )
    Image(
        painter = painter,
        contentDescription = name,
        contentScale = ContentScale.Fit,
        modifier = Modifier.fillMaxSize().padding(8.dp),
        alignment = Alignment.Center,
    )
}

@Composable
fun ActorList(modifier: Modifier = Modifier, actors: List<Credit?>) {
    val actorsSorted = actors.sortedBy { it?.order }
    dimensionResource(id = R.dimen.row_height)
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.End) {
        Text(
            text = "Actors",
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
            modifier = modifier.fillMaxWidth(0.95f),
        )
        LazyRow(modifier = modifier) {
            items(actors.size) {
                Spacer(modifier = Modifier.width(16.dp))
                actorsSorted[it]?.let { it1 -> ActorCard(actor = it1, modifier = Modifier.width(150.dp)) }
            }
        }
    }
}

@Composable
fun ActorCard(actor: Credit, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        ElevatedCard(modifier = Modifier.fillMaxHeight(0.8f).aspectRatio(2 / 3f), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(12.dp)) {
            TwoByThreeAspectRatioImage(imagePath = actor.profilePath ?: "", title = actor.name)
        }
        Text(
            text = actor.name,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            ),
            maxLines = if (expanded) Int.MAX_VALUE else 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.animateContentSize(animationSpec = tween(500)).clickable { expanded = !expanded }.padding(0.dp, 8.dp, 0.dp, 0.dp),
        )
    }
}
