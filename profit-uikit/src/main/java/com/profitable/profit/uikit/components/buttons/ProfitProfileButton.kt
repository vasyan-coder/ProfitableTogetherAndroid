package com.profitable.profit.uikit.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profitable.profit.uikit.theme.ProfitTheme
import com.profitable.profit.uikit.theme.ProfitableTogetherAndroidTheme

/**
 * A composable function representing a profile button styled according to the Profit theme.
 *
 * This button is typically used for accessing user profile-related actions or information.
 *
 * @param onClick Callback function to be invoked when the button is clicked.
 * @param text The text to be displayed on the button.
 * @param modifier Optional modifier for customizing the button's layout and appearance.
 */

@Composable
fun ProfitProfileButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = ProfitTheme.colorScheme.tertiary,
        ),
        contentPadding = PaddingValues(
            horizontal = 32.dp,
            vertical = 8.dp,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color.Black,
        ),
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = ProfitTheme.typography.titleLarge,
        )
    }
}

@Preview
@Composable
private fun ProfitProfileButtonPreview() {
    ProfitableTogetherAndroidTheme {
        ProfitProfileButton(
            onClick = {},
            text = "Начать чат",
        )
    }
}
