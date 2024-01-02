package com.example.moviesandseries.screens.components.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesandseries.R
import com.example.moviesandseries.domain.credits.Credit
import com.example.moviesandseries.screens.components.TwoByThreeAspectRatioImage

/**
 * Composable function to display a list of actors in a column layout.
 *
 * @param modifier Modifier for the entire ActorList.
 * @param actors List of [Credit] objects representing actors.
 */
@Composable
fun ActorList(modifier: Modifier = Modifier, actors: List<Credit?>) {
    val actorsSorted = actors.sortedBy { it?.order }
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.End,
    ) {
        Text(
            text = "Actors",
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
            modifier = modifier.fillMaxWidth(0.95f),
        )
        LazyRow(modifier = modifier) {
            items(actors.size) {
                Spacer(modifier = Modifier.width(18.dp))
                actorsSorted[it]?.let { it1 ->
                    ActorCard(
                        actor = it1,
                        modifier = Modifier.width(dimensionResource(id = R.dimen.detail_card_width)),
                    )
                }
            }
        }
    }
}

/**
 * Composable function to display an actor card with an image, name, and character name.
 *
 * @param actor [Credit] object representing the actor.
 * @param modifier Modifier for the ActorCard.
 */
@Composable
fun ActorCard(actor: Credit, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .aspectRatio(2 / 3f),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(12.dp),
            colors = CardDefaults.elevatedCardColors(MaterialTheme.colorScheme.secondary),
        ) {
            TwoByThreeAspectRatioImage(imagePath = actor.profilePath, title = actor.name)
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
            modifier = Modifier
                .animateContentSize(animationSpec = tween(500))
                .clickable { expanded = !expanded }
                .padding(0.dp, 8.dp, 0.dp, 0.dp),
        )

        Text(
            text = "as ${actor.character}",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            ),
            maxLines = if (expanded) Int.MAX_VALUE else 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .animateContentSize(animationSpec = tween(500))
                .clickable { expanded = !expanded }
                .padding(0.dp, 0.dp, 0.dp, 0.dp),
        )
    }
}
