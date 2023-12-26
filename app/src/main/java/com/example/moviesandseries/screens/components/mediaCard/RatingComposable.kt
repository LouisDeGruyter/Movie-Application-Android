package com.example.moviesandseries.screens.components.mediaCard

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp

@Composable
fun RatingComposable(rating: Double, modifier: Modifier = Modifier) {
    val adjustedRating = (rating * 10).toInt()

    val color = when {
        adjustedRating >= 75 -> Color.Green
        adjustedRating >= 50 -> Color.Yellow
        else -> Color.Red
    }

    val circleSize = 30.dp
    val progress = (rating / 10).toFloat()

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Canvas(
            modifier = Modifier.size(circleSize),
        ) {
            // Draw inner circle with a black border
            val innerCircleRadius = size.width / 2
            val strokeWidth = 8f
            drawCircle(
                color = Color.Black,
                radius = innerCircleRadius,
                center = Offset(innerCircleRadius, innerCircleRadius),
                style = Fill,
            )
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
            drawArc(
                color = color.copy(alpha = 0.5f), // Adjust the alpha for transparency
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

            // Draw the actual inner circle (to cover the black border)

            // Draw text in the center
            drawIntoCanvas {
                val text = (adjustedRating).toString()
                val textSize = 14.dp.toPx()
                val paint = Paint()
                paint.textSize = textSize
                paint.color = Color.White.toArgb()
                val xOffset = (size.width - paint.measureText(text)) / 2
                val yOffset = (size.height + textSize - 10) / 2
                it.nativeCanvas.drawText(text, xOffset, yOffset, paint)
            }
        }
    }
}
