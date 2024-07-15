package com.profitable.profit.uikit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.theme.ProfitTheme
import com.profitable.profit.uikit.theme.ProfitableTogetherAndroidTheme
import java.text.NumberFormat
import java.util.Locale

/**
 * A composable function representing an item in the home view.
 *
 * This item typically displays information about a property or listing.
 *
 * @param onClick Callback function to be invoked when the item is clicked.
 * @param modifier Optional modifier for customizing the item's layout and appearance.
 * @param imageLink The URL link to the image associated with the item.
 * @param title The title or name of the item.
 * @param roomSizes Information about the size or number of rooms in the item.
 * @param address The address or location of the item.
 * @param price The price or cost associated with the item.
 * @param isFavourite Indicates whether the item is marked as a favorite.
 */
@Composable
fun HomeItem(
    onClick: () -> Unit,
    onClickFavourite: () -> Unit,
    modifier: Modifier = Modifier,
    imageLink: String,
    title: String,
    roomSizes: String,
    address: String,
    price: String,
    isFavourite: Boolean,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(40.dp))
            .background(
                color = ProfitTheme.colorScheme.primary,
                shape = RoundedCornerShape(40.dp)
            )
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(40.dp),
            )
            .clickable {
                onClick()
            }
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = imageLink,
                contentDescription = stringResource(id = R.string.visible_image_home),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(40.dp),
                    )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.height(184.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = title,
                        style = ProfitTheme.typography.bodyMedium,
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = roomSizes,
                        style = ProfitTheme.typography.bodySmall,
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = address,
                        style = ProfitTheme.typography.bodySmall,
                        color = Color.White,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    val numberFormat = NumberFormat.getInstance(Locale("ru", "RU"))
                    Text(
                        text = "${numberFormat.format(price.toInt())} ₽/мес.",
                        style = ProfitTheme.typography.bodyLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = TextUnit(-0.5F, TextUnitType.Sp),
                        modifier = Modifier.weight(1f),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        imageVector =
                        if (isFavourite) ImageVector.vectorResource(id = R.drawable.ic_heart_unselected)
                        else ImageVector.vectorResource(id = R.drawable.ic_heart_selected),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(30.dp)
                            .clickable {
                                onClickFavourite()
                            }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}


@Preview
@Composable
private fun HomeItemPreview() {
    ProfitableTogetherAndroidTheme {
        HomeItem(
            onClick = { /*no-op*/ },
            onClickFavourite = { /*no-op*/ },
            imageLink = "https://images.cdn-cian.ru/images/kvartira-moskva-1y-krasnogvardeyskiy-proezd-2056957093-1.jpg",
            title = "Новая дизайнерская квартира",
            roomSizes = "2-комн. квартира, 60 м², 13/21 этаж",
            address = "Беломорская ~ 15 минут пешком",
            price = "50 000 ₽/мес.",
            isFavourite = false,
        )
    }
}
