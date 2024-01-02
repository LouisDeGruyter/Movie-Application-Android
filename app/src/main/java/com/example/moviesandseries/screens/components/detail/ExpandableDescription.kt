package com.example.moviesandseries.screens.components.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.moviesandseries.R

/**
 * Composable function to display an expandable description with a catchphrase.
 *
 * @param modifier Modifier for customizing the layout.
 * @param catchPhrase The catchphrase text to be displayed.
 * @param description The description text to be displayed.
 */
@Composable
fun ExpandableDescription(modifier: Modifier = Modifier, catchPhrase: String, description: String) {
    // State to track the expanded state of the description
    var expanded by remember { mutableStateOf(false) }

    // Display catchphrase and description in a column
    Column(modifier = modifier) {
        // Display catchphrase with a bold font
        Text(
            text = catchPhrase,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sourcesanspro_black)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
            ),
        )

        // Display description with optional max lines and clickable behavior for expanding/collapsing
        Text(
            text = description,
            maxLines = if (expanded) Int.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .animateContentSize(animationSpec = tween(500))
                .clickable { expanded = !expanded },
        )
    }
}
