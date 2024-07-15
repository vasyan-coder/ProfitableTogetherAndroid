package com.profitable.profit.auth.aboutyourself.presentation.compose

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import com.profitable.profit.auth.aboutyourself.presentation.viewmodel.AboutYourSelfViewModel
import com.profitable.profit.auth.aboutyourself.presentation.viewmodel.model.AboutMainEvent
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.components.buttons.ProfitButton
import com.profitable.profit.uikit.components.textfields.ProfitQuestionnaireTextField
import com.profitable.profit.uikit.theme.ProfitTheme

@Composable
fun AboutYourSelfScreen(
    onNavigateToHome: () -> Unit,
    viewModel: AboutYourSelfViewModel,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(all = 16.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(156.dp)
        )
        Text(
            text = stringResource(id = R.string.self_description),
            style = ProfitTheme.typography.headlineLarge,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.questionnaire_description),
            style = ProfitTheme.typography.bodyLarge,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            ProfitQuestionnaireTextField(
                value = viewModel.aboutStateUI.text,
                onValueChange = { viewModel.onEvent(AboutMainEvent.TextChanged(text = it)) },
                modifier = Modifier
                    .fillMaxSize()
            )
            androidx.compose.animation.AnimatedVisibility(
                visible = viewModel.aboutStateUI.isError,
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 50.dp),
            ) {
                Text(

                    text = stringResource(id = R.string.write_something),
                    style = ProfitTheme.typography.titleMedium,
                    color = Color.White,
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProfitButton(
            onClick = {
                if (viewModel.aboutStateUI.text.isNotBlank()) {
                    viewModel.updateDescription()
                    onNavigateToHome()
                } else {
                    viewModel.onEvent(AboutMainEvent.IsError(isError = true))
                }
            },
            text = stringResource(id = R.string.find_place_to_live),
            containerColor = ProfitTheme.colorScheme.secondary,
            modifier = Modifier
                .width(248.dp)
        )
    }
}
