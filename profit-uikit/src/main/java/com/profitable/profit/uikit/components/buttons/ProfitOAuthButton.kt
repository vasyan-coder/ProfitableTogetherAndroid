package com.profitable.profit.uikit.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.theme.ProfitTheme
import com.profitable.profit.uikit.theme.ProfitableTogetherAndroidTheme


/**
 * A composable function representing an OAuth button styled according to the Profit theme.
 *
 * This button is typically used for OAuth authentication within the application.
 *
 * @param onClick Callback function to be invoked when the button is clicked.
 * @param modifier Optional modifier for customizing the button's layout and appearance.
 * @param painter The painter to be used for rendering the button's icon.
 * @param contentDescription A description of the button's content for accessibility purposes.
 */
@Composable
fun ProfitOAuthButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String?,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                color = ProfitTheme.colorScheme.surfaceContainerHighest,
                shape = RoundedCornerShape(25.dp),
            )
            .clip(RoundedCornerShape(25.dp))
            .clickable { onClick() }
            .padding(all = 16.dp),
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
private fun ProfitOAuthButtonPreview() {
    ProfitableTogetherAndroidTheme {
        ProfitOAuthButton(
            onClick = { /* no-op */ },
            painter = painterResource(id = R.drawable.ic_google_logo),
            contentDescription = "Google Sign in",
        )
    }
}
