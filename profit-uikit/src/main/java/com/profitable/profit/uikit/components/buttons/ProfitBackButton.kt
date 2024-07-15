package com.profitable.profit.uikit.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.theme.ProfitTheme
import com.profitable.profit.uikit.theme.ProfitableTogetherAndroidTheme

/**
 * A composable function representing a back button with a profit theme.
 *
 * This button is typically used for navigating back within the application. It displays
 * an arrow icon on a circular background, styled according to the Profit theme.
 *
 * @param onClick Callback function to be invoked when the button is clicked.
 * @param modifier Optional modifier for customizing the button's layout and appearance.
 */
@Composable
fun ProfitBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                color = ProfitTheme.colorScheme.surfaceContainerHighest,
                shape = CircleShape,
            )
            .clip(CircleShape)
            .clickable { onClick() }
            .padding(all = 16.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left),
            contentDescription = null,
            tint = ProfitTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp),
        )
    }
}


@Preview
@Composable
private fun ProfitBackButtonPreview() {
    ProfitableTogetherAndroidTheme {
        ProfitBackButton(onClick = { /* no-op */ })
    }
}
