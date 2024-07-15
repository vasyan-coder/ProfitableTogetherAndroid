package com.profitable.profit.uikit.components.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.theme.ProfitTheme
import com.profitable.profit.uikit.theme.ProfitableTogetherAndroidTheme


/**
 * A composable function representing a Metro chip styled according to the Profit theme.
 *
 * This chip is typically used for displaying Metro-related information or actions.
 *
 * @param onClick Callback function to be invoked when the chip is clicked.
 * @param modifier Optional modifier for customizing the chip's layout and appearance.
 * @param name The name or label to be displayed on the chip.
 * @param color The color to be applied to the Metro icon within the chip.
 */
@Composable
fun ProfitMetroChip(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    name: String,
    color: Color,
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
                color = ProfitTheme.colorScheme.primary,
                shape = RoundedCornerShape(40.dp)
            )
            .clickable {
                onClick()
            }
            .padding(horizontal = 20.dp, vertical = 5.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_metro),
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(32.dp),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = name,
            style = ProfitTheme.typography.titleLarge,
            color = Color.Black,
        )
    }
}

@Preview
@Composable
private fun ProfitMetroChipPreview() {
    ProfitableTogetherAndroidTheme {
        ProfitMetroChip(
            onClick = { /*TODO*/ },
            name = "Киевская",
            color = Color(0xFF009BD5),
        )
    }
}
