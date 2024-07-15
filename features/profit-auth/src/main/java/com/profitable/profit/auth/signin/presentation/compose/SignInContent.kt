package com.profitable.profit.auth.signin.presentation.compose

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
import com.profitable.profit.auth.signin.presentation.viewmodel.SignInViewModel
import com.profitable.profit.auth.signin.presentation.viewmodel.model.SignInMainEvent
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.components.ProfitDivider
import com.profitable.profit.uikit.components.ProfitErrorBox
import com.profitable.profit.uikit.components.buttons.ProfitBackButton
import com.profitable.profit.uikit.components.buttons.ProfitButton
import com.profitable.profit.uikit.components.buttons.ProfitOAuthButton
import com.profitable.profit.uikit.components.textfields.TestProfitTextField
import com.profitable.profit.uikit.theme.ProfitTheme
import kotlinx.coroutines.launch

@Composable
internal fun SignInContent(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel,
    onNavigateToSignUp: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateToHome: () -> Unit,
    onGoogleSignIn: () -> Unit,
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
            text = stringResource(id = R.string.authorizatnion),
            style = ProfitTheme.typography.headlineLarge,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(32.dp))
        SignInForm(
            modifier = Modifier.fillMaxWidth(),
            viewModel = viewModel,
            onNavigateToForgotPassword = onNavigateToForgotPassword,
        )
        Spacer(modifier = Modifier.height(32.dp))
        ProfitButton(
            onClick = {
                viewModel.onEvent(SignInMainEvent.Submit)
                scope.launch {
                    viewModel.isSuccessful.collect {
                        if (it) {
                            onNavigateToHome()
                        }
                    }
                }
            },
            text = stringResource(id = R.string.sign_in),
            modifier = Modifier
                .width(248.dp)
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.height(32.dp))
        ProfitDivider(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.or),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            ProfitOAuthButton(
                onClick = { onGoogleSignIn() },
                painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = null,
                modifier = Modifier.width(164.dp),
            )
            ProfitOAuthButton(
                onClick = { /*TODO*/ },
                painter = painterResource(id = R.drawable.ic_vk_logo),
                contentDescription = null,
                modifier = Modifier.width(164.dp),
            )
        }
        Text(
            text = stringResource(id = R.string.dont_have_acc_sign_up),
            style = ProfitTheme.typography.labelLarge,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(40.dp))
                .clickable {
                    onNavigateToSignUp()
                },
        )
    }
}

@Composable
private fun SignInForm(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel,
    onNavigateToForgotPassword: () -> Unit,
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
            value = viewModel.formState.email,
            onValueChange = { viewModel.onEvent(SignInMainEvent.EmailChanged(email = it)) },
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
            onValueChange = { viewModel.onEvent(SignInMainEvent.PasswordChanged(password = it)) },
            placeholder = stringResource(id = R.string.password),
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_lock),
                    contentDescription = null,
                    tint = ProfitTheme.colorScheme.primary,
                    modifier = Modifier.size(30.dp)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
                text = stringResource(id = R.string.forgot_password),
                style = ProfitTheme.typography.labelLarge,
                color = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(40.dp))
                    .clickable { onNavigateToForgotPassword() },
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
                        viewModel.onEvent(SignInMainEvent.VisiblePassword(!(viewModel.formState.isVisiblePassword)))
                    },
            )
        }
    }
}
