package com.course.profit.main.aboutedit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.course.profit.main.aboutedit.viewmodel.AboutEditViewModel
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.components.buttons.ProfitButton
import com.profitable.profit.uikit.components.textfields.ProfitQuestionnaireTextField
import com.profitable.profit.uikit.theme.ProfitTheme

@Composable
fun AboutEditScreen(
    onNavigateToMyProfile: () -> Unit,
    viewModel: AboutEditViewModel,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 96.dp),
    ) {
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
                .weight(1f)
        ) {
            ProfitQuestionnaireTextField(
                value = viewModel.description,
                onValueChange = { viewModel.description = it },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            ProfitButton(
                onClick = {
                    onNavigateToMyProfile()
                },
                text = stringResource(id = R.string.cancel),
                containerColor = ProfitTheme.colorScheme.secondary,
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.width(32.dp))
            ProfitButton(
                onClick = {
                    if (viewModel.description.isNotBlank()) {
                        viewModel.updateDescription()
                        onNavigateToMyProfile()
                    }
                },
                text = stringResource(id = R.string.save),
                containerColor = ProfitTheme.colorScheme.secondary,
                modifier = Modifier.weight(1f),
            )
        }
    }
}
