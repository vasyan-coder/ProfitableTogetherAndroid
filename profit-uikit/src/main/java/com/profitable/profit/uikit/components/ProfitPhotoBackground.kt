package com.profitable.profit.uikit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.theme.ProfitTheme

/**
 * A composable function representing a background with a gradient overlay and an image overlay styled according to the Profit theme.
 *
 * This background is typically used in the home view.
 *
 * @param modifier Optional modifier for customizing the background's layout and appearance.
 */
@Composable
fun ProfitPhotoBackground(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .drawWithContent {
                val colorsStops = arrayOf(
                    0.45f to Color(0x00A5A09D),
                    0.8f to Color(0xFFA5A09D),
                )
                drawContent()
                drawRect(
                    brush = Brush.verticalGradient(colorStops = colorsStops),
                )
            },
    ) {
        Image(
            painterResource(R.drawable.image_greeting),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.65f)
                .background(ProfitTheme.colorScheme.surface)
        )
    }
}
