package com.profitable.profit.uikit.components


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.theme.ProfitTheme
import com.profitable.profit.uikit.theme.ProfitableTogetherAndroidTheme

// TODO("add arrow animate and state")
@Composable
fun ProfitFoldingButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    isOpen: Boolean,
) {
    val angle by animateFloatAsState(
        targetValue = if (isOpen) 180f else 0f,
        animationSpec = spring(),
        label = ""
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(40.dp))
            .background(ProfitTheme.colorScheme.tertiary)
            .border(
                width = 0.5.dp,
                color = ProfitTheme.colorScheme.surfaceContainerHighest,
                shape = RoundedCornerShape(40.dp)
            )
            .clickable {
                onClick()
            }
            .padding(vertical = 5.dp, horizontal = 32.dp)
    ) {
        Text(
            text = text,
            style = ProfitTheme.typography.titleLarge,
            color = ProfitTheme.colorScheme.surfaceContainerHighest,
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow),
            contentDescription = null,
            tint = ProfitTheme.colorScheme.surfaceContainerHighest,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterEnd)
                .rotate(angle)
        )
    }
}

@Preview
@Composable
private fun ProfitFoldingButtonPreview() {
    ProfitableTogetherAndroidTheme {
        ProfitFoldingButton(
            onClick = { /*TODO*/ },
            text = "Забронировано",
            isOpen = false,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
