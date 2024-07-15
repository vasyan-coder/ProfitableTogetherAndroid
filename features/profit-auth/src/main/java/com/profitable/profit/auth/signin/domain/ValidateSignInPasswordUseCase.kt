package com.profitable.profit.auth.signin.domain

import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.utils.UiText
import javax.inject.Inject

class ValidateSignInPasswordUseCase @Inject constructor() {
    operator fun invoke(input: String): SignInValidationResult {
        if (input.isBlank()) {
            return SignInValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.password_empty_error),
            )
        }
        return SignInValidationResult(
            successful = true,
            errorMessage = null,
        )
    }
}
