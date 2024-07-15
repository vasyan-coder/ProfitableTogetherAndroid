package com.profitable.profit.auth.signup.presentation.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.profitable.profit.auth.signup.presentation.viewmodel.SignUpViewModel
import com.profitable.profit.auth.signup.presentation.viewmodel.model.SignUpMainEvent
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.components.ProfitErrorBox
import com.profitable.profit.uikit.components.buttons.ProfitBackButton
import com.profitable.profit.uikit.components.buttons.ProfitButton
import com.profitable.profit.uikit.components.textfields.TestProfitTextField
import com.profitable.profit.uikit.theme.ProfitTheme
import kotlinx.coroutines.launch

@Composable
internal fun SignUpContent(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel,
    onNavigateToAboutYourself: () -> Unit,
    onBackPressed: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier,
    ) {
        ProfitBackButton(onClick = { onBackPressed() })
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(172.dp)
        )
        Text(
            text = stringResource(id = R.string.registration),
            style = ProfitTheme.typography.headlineLarge,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(32.dp))
        SignUpForm(
            modifier = Modifier.fillMaxWidth(),
            viewModel = viewModel,
        )
        Spacer(modifier = Modifier.height(32.dp))
        ProfitButton(
            onClick = {
                viewModel.onEvent(SignUpMainEvent.Submit)

                scope.launch {
                    viewModel.isRegistered.collect {
                        if (it) {
                            onNavigateToAboutYourself()
                        }
                    }
                }
            },
            text = stringResource(id = R.string.create_account),
            modifier = Modifier
                .width(248.dp)
                .align(Alignment.CenterHorizontally),
        )
    }
}

@Composable
private fun SignUpForm(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel,
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier,
    ) {
        AnimatedVisibility(visible = viewModel.formState.error != null) {
            ProfitErrorBox(
                message = viewModel.formState.error?.asString(),
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TestProfitTextField(
            value = viewModel.formState.name,
            onValueChange = { viewModel.onEvent(SignUpMainEvent.NameChanged(name = it)) },
            placeholder = stringResource(id = R.string.full_name),
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_user),
                    contentDescription = null,
                    tint = ProfitTheme.colorScheme.primary,
                    modifier = Modifier.size(30.dp)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        TestProfitTextField(
            value = viewModel.formState.email,
            onValueChange = { viewModel.onEvent(SignUpMainEvent.EmailChanged(email = it)) },
            placeholder = stringResource(id = R.string.email),
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_email),
                    contentDescription = null,
                    tint = ProfitTheme.colorScheme.primary,
                    modifier = Modifier.size(30.dp)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        TestProfitTextField(
            value = viewModel.formState.password,
            onValueChange = { viewModel.onEvent(SignUpMainEvent.PasswordChanged(password = it)) },
            placeholder = stringResource(id = R.string.password),
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_lock),
                    contentDescription = null,
                    tint = ProfitTheme.colorScheme.primary,
                    modifier = Modifier.size(30.dp)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
            ),
            modifier = Modifier.fillMaxWidth(),
            isVisible = viewModel.formState.isVisiblePassword,
        )
        Spacer(modifier = Modifier.height(8.dp))
        TestProfitTextField(
            value = viewModel.formState.chatLink,
            onValueChange = { viewModel.onEvent(SignUpMainEvent.ChatLinkChanged(chatLink = it)) },
            placeholder = stringResource(id = R.string.link_to_chat),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            modifier = Modifier.fillMaxWidth(),
            isVisible = viewModel.formState.isVisiblePassword,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.company_policy),
                style = ProfitTheme.typography.labelLarge,
                color = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(40.dp))
                    .clickable { TODO() },
            )
            Text(
                text = if (viewModel.formState.isVisiblePassword)
                    stringResource(id = R.string.hide_password)
                else
                    stringResource(id = R.string.show_password),
                style = ProfitTheme.typography.labelLarge,
                color = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(40.dp))
                    .clickable {
                        viewModel.onEvent(SignUpMainEvent.VisiblePassword(!(viewModel.formState.isVisiblePassword)))
                    },
            )
        }
    }
}
