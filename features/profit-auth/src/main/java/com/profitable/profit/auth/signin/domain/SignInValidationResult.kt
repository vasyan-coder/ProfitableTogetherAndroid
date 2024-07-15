package com.profitable.profit.auth.signin.domain

import com.profitable.profit.uikit.utils.UiText


data class SignInValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null,
)
