package com.profitable.profit.uikit.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.profitable.profit.uikit.theme.ProfitableTogetherAndroidTheme

/**
 * A composable function representing a photo item in the home view styled according to the Profit theme.
 *
 * This item typically displays a photo associated with a property or listing.
 *
 * @param modifier Optional modifier for customizing the item's layout and appearance.
 * @param imageUrl The URL link to the image to be displayed.
 */
@Composable
fun ProfitHomePhotoItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(shape = RoundedCornerShape(40.dp))
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(40.dp),
            ),
    )
}

@Preview
@Composable
private fun ProfitHomePhotoItemPreview() {
    ProfitableTogetherAndroidTheme {
        ProfitHomePhotoItem(
            imageUrl = "https://images.cdn-cian.ru/images/kvartira-moskva-1y-krasnogvardeyskiy-proezd-2056957093-1.jpg",
            modifier = Modifier.height(200.dp),
        )
    }
}
