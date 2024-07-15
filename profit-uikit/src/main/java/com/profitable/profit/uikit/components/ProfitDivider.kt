package com.profitable.profit.uikit.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profitable.profit.uikit.theme.ProfitTheme
import com.profitable.profit.uikit.theme.ProfitableTogetherAndroidTheme

/**
 * A composable function representing a divider with text styled according to the Profit theme.
 *
 * This divider is typically used to visually separate sections of content.
 *
 * @param modifier Optional modifier for customizing the divider's layout and appearance.
 * @param text The text to be displayed within the divider.
 */
@Composable
fun ProfitDivider(
    modifier: Modifier = Modifier,
    text: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        HorizontalDivider(
            color = Color.White,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = text,
            color = Color.White,
            style = ProfitTheme.typography.labelLarge,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 3.dp),
        )
        HorizontalDivider(
            color = Color.White,
            modifier = Modifier.weight(1f),
        )
    }
}

@Preview
@Composable
private fun ProfitDividerPreview() {
    ProfitableTogetherAndroidTheme {
        ProfitDivider(
            text = "or",
        )
    }
}
