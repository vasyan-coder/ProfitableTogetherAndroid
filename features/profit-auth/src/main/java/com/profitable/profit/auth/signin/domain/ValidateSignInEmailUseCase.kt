package com.profitable.profit.auth.signin.domain

import android.util.Patterns
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.utils.UiText
import javax.inject.Inject

class ValidateSignInEmailUseCase @Inject constructor() {
    operator fun invoke(input: String): SignInValidationResult {
        if (input.isBlank()) {
            return SignInValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.email_empty_error),
            )
        }
        if (!isEmailValid(input)) {
            return SignInValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.email_error),
            )
        }
        return SignInValidationResult(
            successful = true,
            errorMessage = null,
        )
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
