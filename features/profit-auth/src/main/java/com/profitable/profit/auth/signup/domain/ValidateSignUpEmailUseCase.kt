package com.profitable.profit.auth.signup.domain

import android.util.Patterns
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.utils.UiText
import javax.inject.Inject

class ValidateSignUpEmailUseCase @Inject constructor() {
    operator fun invoke(input: String): SignUpValidationResult {
        if (input.isBlank()) {
            return SignUpValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.email_empty_error),
            )
        }
        if (!isEmailValid(input)) {
            return SignUpValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.email_error),
            )
        }
        return SignUpValidationResult(
            successful = true,
            errorMessage = null,
        )
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
