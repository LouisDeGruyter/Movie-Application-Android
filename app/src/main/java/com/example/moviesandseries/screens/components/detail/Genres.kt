package com.example.moviesandseries.screens.components.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun Genres(modifier: Modifier = Modifier, genres: List<String>) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        genres.forEach { genre ->
            if (genre.isEmpty()) return
            Text(
                text = genre,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .border(2.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(50))
                    .padding(8.dp, 3.dp),
            )
            if (genre != genres.last()) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}