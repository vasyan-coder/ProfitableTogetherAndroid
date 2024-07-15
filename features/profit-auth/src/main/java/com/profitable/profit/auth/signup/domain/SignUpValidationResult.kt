package com.profitable.profit.auth.signup.domain

import com.profitable.profit.uikit.utils.UiText


data class SignUpValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null,
)
