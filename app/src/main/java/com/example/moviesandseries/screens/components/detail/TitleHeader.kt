package com.example.moviesandseries.screens.components.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesandseries.R

@Composable
fun TitleHeader(
    modifier: Modifier = Modifier,
    title: String,
    releaseDate: String,
    runtime: Int = 0,
    numberOfSeasons: Int = 0,
) {
    val normalTextStyleCentered = TextStyle(
        fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
    )
    Column(
        modifier = modifier.padding(PaddingValues(0.dp, 8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),

        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (releaseDate.length > 4) {
                Text(
                    text = releaseDate.substring(0, 4) + " | ",
                    style = normalTextStyleCentered,
                )
            }
            if (runtime > 0) {
                Text(
                    text = "$runtime min",
                    style = normalTextStyleCentered,
                )
            }
            if (numberOfSeasons > 0) {
                Text(
                    text = "$numberOfSeasons seasons",
                    style = normalTextStyleCentered,
                )
            }
        }
    }
}
