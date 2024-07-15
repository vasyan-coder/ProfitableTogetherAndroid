package com.profitable.profit.uikit.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
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
 * A composable function representing a search action button styled according to the Profit theme.
 *
 * This button is typically used for triggering search-related actions within the application.
 *
 * @param onClick Callback function to be invoked when the button is clicked.
 * @param modifier Optional modifier for customizing the button's layout and appearance.
 * @param text The text to be displayed on the button.
 * @param enabled Whether the button is currently enabled and clickable.
 */
@Composable
fun ProfitSearchActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = ProfitTheme.colorScheme.primary,
        ),
        contentPadding = PaddingValues(
            start = 32.dp,
            top = 8.dp,
            end = 32.dp,
            bottom = 12.dp
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color.White,
        ),
        enabled = enabled,
        modifier = modifier,
    ) {
        Text(
            text = text.lowercase(),
            style = ProfitTheme.typography.headlineMedium,
        )
    }
}


@Preview
@Composable
private fun ProfitSearchActionButtonPreview() {
    ProfitableTogetherAndroidTheme {
        ProfitSearchActionButton(
            onClick = { /*TODO*/ },
            text = "Поиск",
            modifier = Modifier.width(200.dp)
        )
    }
}
