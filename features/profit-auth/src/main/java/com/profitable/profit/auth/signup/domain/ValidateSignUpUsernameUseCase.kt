package com.profitable.profit.auth.signup.domain

import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.utils.UiText
import javax.inject.Inject

class ValidateSignUpUsernameUseCase @Inject constructor() {
    operator fun invoke(input: String): SignUpValidationResult {
        if (input.isBlank()) {
            return SignUpValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.full_name_error)
            )
        }

        return SignUpValidationResult(
            successful = true,
            errorMessage = null,
        )
    }
}
