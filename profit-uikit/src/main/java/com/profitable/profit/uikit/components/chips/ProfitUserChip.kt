package com.profitable.profit.uikit.components.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.profitable.profit.uikit.theme.ProfitTheme
import com.profitable.profit.uikit.theme.ProfitableTogetherAndroidTheme

/**
 * A composable function representing a user chip styled according to the Profit theme.
 *
 * This chip is typically used for displaying user-related information or actions.
 *
 * @param onClick Callback function to be invoked when the chip is clicked.
 * @param modifier Optional modifier for customizing the chip's layout and appearance.
 * @param name The name or label of the user to be displayed on the chip.
 * @param profilePhotoLink The URL link to the user's profile photo.
 */
@Composable
fun ProfitUserChip(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    name: String,
    profilePhotoLink: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .background(
                color = ProfitTheme.colorScheme.surfaceContainerHighest,
                shape = RoundedCornerShape(40.dp),
            )
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(40.dp)
            )
            .clip(RoundedCornerShape(40.dp))
            .clickable {
                onClick()
            }
            .padding(start = 4.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(
                    color = ProfitTheme.colorScheme.primary,
                ),
        ) {
            AsyncImage(
                model = profilePhotoLink,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = name,
            style = ProfitTheme.typography.titleLarge,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.width(4.dp))
    }
}

@Preview
@Composable
private fun ProfitUserChipPreview() {
    ProfitableTogetherAndroidTheme {
        ProfitUserChip(
            onClick = { /*TODO*/ },
            name = "John Doe",
            profilePhotoLink = "",
        )
    }
}
