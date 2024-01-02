package com.example.moviesandseries.screens.components.index.mediaCard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer

/**
 * Composable function for displaying a circular rating indicator with a numeric rating value.
 *
 * @param rating The numeric rating value (out of 10).
 * @param modifier Modifier for customizing the layout.
 */
@Composable
fun RatingComposable(rating: Double, modifier: Modifier = Modifier) {
    // Adjust the rating to be out of 100
    val adjustedRating = (rating * 10).toInt()

    // Determine the color based on the adjusted rating
    val color = when {
        adjustedRating >= 75 -> Color.Green
        adjustedRating >= 50 -> Color.Yellow
        else -> Color.Red
    }

    // Calculate the progress for drawing the arc
    val progress = (rating / 10).toFloat()

    // Convert the adjusted rating to a string for display
    val text = adjustedRating.toString()

    // Initialize the TextMeasurer to measure the text size
    val textMeasurer = rememberTextMeasurer()

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Canvas(
            modifier = Modifier.fillMaxWidth(0.2f).aspectRatio(1f),
        ) {
            val fontSize = size.height / 2
            val fontSizeSp = fontSize.toSp()

            // Style for the rating text
            val style = TextStyle(fontSize = fontSizeSp, color = Color.White)

            // Measure the text layout
            val textLayoutResult: TextLayoutResult =
                textMeasurer.measure(text = AnnotatedString(text), style)

            // Get the size of the text
            val textSize = textLayoutResult.size

            // Draw inner circle with a black border
            val innerCircleRadius = size.width / 2
            val strokeWidth = size.height / 15

            drawCircle(
                color = Color.Black,
                radius = innerCircleRadius,
                center = Offset(innerCircleRadius, innerCircleRadius),
                style = Fill,
            )

            // Draw colored arc representing the rating progress
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360f * progress,
                useCenter = false,
                style = Stroke(strokeWidth, cap = StrokeCap.Round),
                topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                size = Size(
                    (innerCircleRadius - strokeWidth / 2) * 2,
                    (innerCircleRadius - strokeWidth / 2) * 2,
                ),
            )

            // Draw transparent arc for remaining progress
            drawArc(
                color = color.copy(alpha = 0.5f),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(strokeWidth, cap = StrokeCap.Round),
                topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                size = Size(
                    (innerCircleRadius - strokeWidth / 2) * 2,
                    (innerCircleRadius - strokeWidth / 2) * 2,
                ),
            )

            // Draw text in the center
            drawText(
                textMeasurer = textMeasurer,
                text = text,
                style = style,
                topLeft = Offset(
                    center.x - textSize.width / 2f,
                    center.y - textSize.height / 2f,
                ),
            )
        }
    }
}
