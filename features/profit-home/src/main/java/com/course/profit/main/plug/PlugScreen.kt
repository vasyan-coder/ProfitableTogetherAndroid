package com.course.profit.main.plug

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.components.ProfitPhotoBackground
import com.profitable.profit.uikit.components.buttons.ProfitButton
import com.profitable.profit.uikit.theme.ProfitTheme

@Composable
fun PlugScreen(
    onNavigateToSignIn: () -> Unit,
    onNavigateToSignUp: () -> Unit,
) {
    Box {
        ProfitPhotoBackground(modifier = Modifier.fillMaxSize())
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 128.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .weight(0.5f)
                    .size(216.dp),
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(
                    text = stringResource(id = R.string.plug_hint),
                    color = Color.White,
                    style = ProfitTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(248.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                ProfitButton(
                    onClick = { onNavigateToSignIn() },
                    text = stringResource(id = R.string.sign_in),
                    modifier = Modifier.width(248.dp),
                )
                Spacer(modifier = Modifier.height(8.dp))
                ProfitButton(
                    onClick = { onNavigateToSignUp() },
                    text = stringResource(id = R.string.create_account),
                    modifier = Modifier.width(248.dp),
                )
            }
        }
    }
}
