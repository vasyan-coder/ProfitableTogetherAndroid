package com.profitable.profit.auth.signin.presentation.model

import com.profitable.profit.uikit.utils.UiText


data class SignInStateUI(
    val email: String = "",
    val password: String = "",
    val error: UiText? = null,
    val isVisiblePassword: Boolean = false,
    val isSuccessful: Boolean = false,
)
