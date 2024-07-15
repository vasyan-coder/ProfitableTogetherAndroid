package com.profitable.profit.auth.signup.domain

import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.utils.UiText
import javax.inject.Inject

class ValidateSignUpPasswordUseCase @Inject constructor() {
    operator fun invoke(input: String): SignUpValidationResult {
        if (input.length < 8) {
            return SignUpValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.password_length_error),
            )
        }
        if (!isPasswordValid(input)) {
            return SignUpValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.password_registration_error),
            )
        }

        return SignUpValidationResult(
            successful = true,
            errorMessage = null,
        )
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.any { it.isDigit() } && password.any { it.isLetter() }
    }
}
