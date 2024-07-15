package com.profitable.profit.auth.signin.presentation.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.profitable.profit.auth.signin.presentation.viewmodel.SignInViewModel

@Composable
fun SignInScreen(
    onNavigateToSignUp: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateToHome: () -> Unit,
    onGoogleSignIn: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: SignInViewModel,
) {
    SignInContent(
        viewModel = viewModel,
        onNavigateToSignUp = onNavigateToSignUp,
        onNavigateToForgotPassword = onNavigateToForgotPassword,
        onNavigateToHome = onNavigateToHome,
        onGoogleSignIn = onGoogleSignIn,
        onBackPressed = onBackPressed,
        modifier = Modifier.padding(all = 16.dp),
    )
}
