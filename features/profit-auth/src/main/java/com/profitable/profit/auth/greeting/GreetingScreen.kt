package com.profitable.profit.auth.greeting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.components.ProfitPhotoBackground
import com.profitable.profit.uikit.components.buttons.ProfitButton
import com.profitable.profit.uikit.theme.ProfitTheme
import com.profitable.profit.uikit.theme.ProfitableTogetherAndroidTheme


@Composable
fun GreetingScreen(
    onNavigateToSignIn: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onNavigateToHome: () -> Unit,
) {
    Box {
        ProfitPhotoBackground(modifier = Modifier.fillMaxSize())
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .size(216.dp),
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ProfitButton(
                    onClick = { onNavigateToHome() },
                    text = stringResource(id = R.string.find_place_to_live),
                    containerColor = ProfitTheme.colorScheme.secondary,
                    modifier = Modifier.width(248.dp),
                )
                Spacer(modifier = Modifier.height(8.dp))
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
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Made with love",
                    style = ProfitTheme.typography.labelSmall,
                    color = Color.White,
                )
                Text(
                    text = "v.1.0",
                    style = ProfitTheme.typography.labelSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview
@Composable
private fun GreetingScreenPreview() {
    ProfitableTogetherAndroidTheme {
        Surface {
            GreetingScreen(
                onNavigateToSignIn = {},
                onNavigateToSignUp = {},
                onNavigateToHome = {},
            )
        }
    }
}
