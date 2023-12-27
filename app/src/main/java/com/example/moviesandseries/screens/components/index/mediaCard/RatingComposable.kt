package com.example.moviesandseries.screens.components.index.mediaCard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.sp

@Composable
fun RatingComposable(rating: Double, modifier: Modifier = Modifier) {
    val adjustedRating = (rating * 10).toInt()
    val color = when {
        adjustedRating >= 75 -> Color.Green
        adjustedRating >= 50 -> Color.Yellow
        else -> Color.Red
    }
    val progress = (rating / 10).toFloat()
    val text = adjustedRating.toString()
    val textMeasurer = rememberTextMeasurer()
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Canvas(
            modifier = Modifier.fillMaxWidth(0.2f).aspectRatio(1f),
        ) {
            val fontsize = size.height / 2
            val fontSizeSp = fontsize.toSp()
            val style = TextStyle(fontSize = fontSizeSp, color = Color.White)
            val textLayoutResult: TextLayoutResult =
                textMeasurer.measure(text = AnnotatedString(text), style)
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
