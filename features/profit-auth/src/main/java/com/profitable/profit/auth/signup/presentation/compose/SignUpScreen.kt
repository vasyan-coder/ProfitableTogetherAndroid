package com.profitable.profit.auth.signup.presentation.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.profitable.profit.auth.signup.presentation.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(
    onNavigateToAboutYourself: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: SignUpViewModel,
) {
    SignUpContent(
        viewModel = viewModel,
        onNavigateToAboutYourself = onNavigateToAboutYourself,
        onBackPressed = onBackPressed,
        modifier = Modifier.padding(all = 16.dp),
    )
}
