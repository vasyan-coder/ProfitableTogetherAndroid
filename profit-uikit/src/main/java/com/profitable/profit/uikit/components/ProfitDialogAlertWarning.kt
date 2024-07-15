package com.profitable.profit.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.profitable.profit.uikit.theme.ProfitTheme

@Composable
fun ProfitDialogAlertWarning(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .clip(RoundedCornerShape(40.dp))
                .background(color = ProfitTheme.colorScheme.surfaceContainerHighest)
                .border(
                    width = 1.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(40.dp),
                )
                .padding(20.dp)
        ) {
            Text(
                text = "Вы уже забронировали 3 объявления. Чтобы добавить новую бронь, уберите любую бронь с любого объявления.",
                style = ProfitTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = Color.Black,
            )
        }
    }
}
