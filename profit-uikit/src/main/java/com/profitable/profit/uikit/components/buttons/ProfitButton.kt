package com.profitable.profit.uikit.components.buttons

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
 * A composable function representing a custom button styled according to the Profit theme.
 *
 * This button is typically used for triggering actions within the application.
 *
 * @param onClick Callback function to be invoked when the button is clicked.
 * @param modifier Optional modifier for customizing the button's layout and appearance.
 * @param text The text to be displayed on the button.
 * @param containerColor The color of the button's background.
 * @param enabled Whether the button is currently enabled and clickable.
 */
@Composable
fun ProfitButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    containerColor: Color = ProfitTheme.colorScheme.primary,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
        ),
        contentPadding = PaddingValues(
            horizontal = 32.dp,
            vertical = 12.dp,
        ),
        enabled = enabled,
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
private fun ProfitButtonPreview() {
    ProfitableTogetherAndroidTheme {
        ProfitButton(
            text = "Button",
            onClick = { /* no-op */ },
        )
    }
}
