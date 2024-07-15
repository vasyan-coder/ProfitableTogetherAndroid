package com.profitable.profit.uikit.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.theme.ProfitTheme
import com.profitable.profit.uikit.theme.ProfitableTogetherAndroidTheme


/**
 * A composable function representing a favourite button styled according to the Profit theme.
 *
 * This button allows users to mark or unmark an item as a favourite.
 *
 * @param onClick Callback function to be invoked when the button is clicked.
 * @param modifier Optional modifier for customizing the button's layout and appearance.
 * @param enabled Whether the button is currently enabled and clickable.
 * @param isFavourite Indicates whether the item associated with the button is currently marked as a favourite.
 */
@Composable
fun ProfitFavouriteButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isFavourite: Boolean,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = ProfitTheme.colorScheme.tertiary,
        ),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp,
        ),
        enabled = enabled,
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(40.dp)
            ),
    ) {
        Image(
            painter = if (isFavourite)
                painterResource(id = R.drawable.ic_heart_unselected)
            else
                painterResource(id = R.drawable.ic_heart_selected),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(id = R.string.favourite),
            style = ProfitTheme.typography.titleLarge,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun ProfitFavouriteButtonSelected() {
    ProfitableTogetherAndroidTheme {
        ProfitFavouriteButton(
            onClick = {},
            isFavourite = true,
        )
    }
}

@Preview
@Composable
private fun ProfitFavouriteButtonUnSelected() {
    ProfitableTogetherAndroidTheme {
        ProfitFavouriteButton(
            onClick = {},
            isFavourite = false,
        )
    }
}
