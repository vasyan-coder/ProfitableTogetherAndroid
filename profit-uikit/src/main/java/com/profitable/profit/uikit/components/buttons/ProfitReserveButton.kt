package com.profitable.profit.uikit.components.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.theme.ProfitTheme
import com.profitable.profit.uikit.theme.ProfitableTogetherAndroidTheme

@Composable
fun ProfitReserveButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isReserved: Boolean,
    profitReserveButtonState: ProfitReserveButtonState,
) {
    when (profitReserveButtonState) {
        ProfitReserveButtonState.Loading -> {
            Box {
                Button(
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isReserved) ProfitTheme.colorScheme.tertiary else ProfitTheme.colorScheme.surfaceContainerHighest,
                    ),
                    contentPadding = PaddingValues(
                        horizontal = if (isReserved) 4.dp else 16.dp,
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
                    if (isReserved) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_checkmark),
                            contentDescription = null,
                            tint = Color.White,
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    Text(
                        text = if (isReserved) stringResource(id = R.string.reserved) else stringResource(
                            id = R.string.reserve
                        ),
                        style = ProfitTheme.typography.titleLarge,
                        color = if (isReserved) Color.White else Color.Black,
                        modifier = Modifier.alpha(0.2f)
                    )
                }
                CircularProgressIndicator(
                    color = ProfitTheme.colorScheme.tertiary,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        ProfitReserveButtonState.NotReserved -> {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isReserved) ProfitTheme.colorScheme.tertiary else ProfitTheme.colorScheme.surfaceContainerHighest,
                ),
                contentPadding = PaddingValues(
                    horizontal = if (isReserved) 4.dp else 16.dp,
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
                if (isReserved) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_checkmark),
                        contentDescription = null,
                        tint = Color.White,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Text(
                    text = if (isReserved) stringResource(id = R.string.reserved) else stringResource(
                        id = R.string.reserve
                    ),
                    style = ProfitTheme.typography.titleLarge,
                    color = if (isReserved) Color.White else Color.Black,
                )
            }
        }

        ProfitReserveButtonState.Reserved -> {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isReserved) ProfitTheme.colorScheme.tertiary else ProfitTheme.colorScheme.surfaceContainerHighest,
                ),
                contentPadding = PaddingValues(
                    horizontal = if (isReserved) 4.dp else 16.dp,
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
                if (isReserved) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_checkmark),
                        contentDescription = null,
                        tint = Color.White,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Text(
                    text = if (isReserved) stringResource(id = R.string.reserved) else stringResource(
                        id = R.string.reserve
                    ),
                    style = ProfitTheme.typography.titleLarge,
                    color = if (isReserved) Color.White else Color.Black,
                )
            }
        }
    }
}

sealed class ProfitReserveButtonState {
    data object NotReserved : ProfitReserveButtonState()
    data object Loading : ProfitReserveButtonState()
    data object Reserved : ProfitReserveButtonState()
}

@Preview
@Composable
private fun ProfitReserveButtonNotReservedPreview() {
    ProfitableTogetherAndroidTheme {
        ProfitReserveButton(
            onClick = { /* no-op */ },
            isReserved = false,
            profitReserveButtonState = ProfitReserveButtonState.Loading
        )
    }
}

@Preview
@Composable
private fun ProfitReserveButtonReservedPreview() {
    ProfitableTogetherAndroidTheme {
        ProfitReserveButton(
            onClick = { /* no-op */ },
            isReserved = true,
            profitReserveButtonState = ProfitReserveButtonState.Reserved
        )
    }
}
