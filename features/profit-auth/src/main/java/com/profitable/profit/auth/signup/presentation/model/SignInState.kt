package com.profitable.profit.auth.signup.presentation.model

import com.profitable.profit.uikit.utils.UiText


data class SignUpStateUI(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val chatLink: String = "",
    val error: UiText? = null,
    val isVisiblePassword: Boolean = false,
    val isSuccessful: Boolean = false,
)
