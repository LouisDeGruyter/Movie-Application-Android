package com.example.moviesandseries.screens.components.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Cases
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.moviesandseries.R
import com.example.moviesandseries.domain.ProductionCompany
import com.example.moviesandseries.network.ApiEndpoints

/**
 * Composable function to display production companies with their logo and name.
 *
 * @param modifier Modifier for customizing the layout.
 * @param productionCompanies List of production companies to be displayed.
 */
@Composable
fun ProductionCompanies(modifier: Modifier = Modifier, productionCompanies: List<ProductionCompany?>) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.End,
    ) {
        Text(
            text = "Production Companies",
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
            modifier = modifier.fillMaxWidth(0.95f),
        )
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(productionCompanies.size) {
                productionCompanies[it]?.let { it1 ->
                    Spacer(modifier = Modifier.width(18.dp))
                    ProductionCompanyCard(
                        productionCompany = it1,
                        modifier = Modifier.width(140.dp),
                    )
                }
            }
        }
    }
}

/**
 * Composable function to display a card for a production company with its logo and name.
 *
 * @param modifier Modifier for customizing the layout.
 * @param productionCompany Production company details to be displayed.
 */
@Composable
fun ProductionCompanyCard(modifier: Modifier = Modifier, productionCompany: ProductionCompany) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .aspectRatio(1f),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(12.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = if (isSystemInDarkTheme()) Color.DarkGray else MaterialTheme.colorScheme.primary
            ),
        ) {
            // Display the production company's image using ProductionCompanyImage composable
            ProductionCompanyImage(
                imagePath = productionCompany.logoPath,
                name = productionCompany.name,
            )
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
            modifier = Modifier
                .animateContentSize(animationSpec = tween(500))
                .clickable { expanded = !expanded }
                .padding(0.dp, 8.dp, 0.dp, 0.dp),
        )
    }
}

/**
 * Composable function to display the image of a production company.
 *
 * @param imagePath Logo path of the production company.
 * @param name Name of the production company for content description.
 */
@Composable
fun ProductionCompanyImage(imagePath: String, name: String) {
    // Use rememberAsyncImagePainter to load and display the image asynchronously
    var painter = rememberAsyncImagePainter(
        model = "",
        error = rememberVectorPainter(image = Icons.Filled.Cases),
    )
    // If the imagePath is not empty, load the image using Coil library
    if (imagePath.isNotEmpty()) {
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(ApiEndpoints.Poster + imagePath)
                .crossfade(500)
                .build(),
            error = rememberVectorPainter(image = Icons.Filled.BrokenImage),
            placeholder = rememberVectorPainter(image = Icons.Filled.Cases),
        )
    }

    // Display the image using the Image composable
    Image(
        painter = painter,
        contentDescription = name,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        alignment = Alignment.Center,
    )
}
