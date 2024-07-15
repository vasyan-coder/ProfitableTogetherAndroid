package com.profitable.profit.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.theme.ProfitTheme


/**
 * A composable function representing an error box styled according to the Profit theme.
 *
 * This box is typically used for displaying error messages.
 *
 * @param modifier Optional modifier for customizing the box's layout and appearance.
 * @param message The error message to be displayed. If null, a default error message is shown.
 */
@Composable
fun ProfitErrorBox(
    modifier: Modifier = Modifier,
    message: String?,
) {
    Box(
        modifier = modifier
            .background(ProfitTheme.colorScheme.primary, RoundedCornerShape(40.dp)),
    ) {
        Text(
            text = message
                ?: stringResource(id = R.string.something_was_wrong_error),
            style = ProfitTheme.typography.bodyLarge,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
        )
    }
}
