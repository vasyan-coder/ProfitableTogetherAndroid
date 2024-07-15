package com.profitable.profit.auth.signin.presentation.viewmodel.model

sealed class SignInMainEvent {
    data class EmailChanged(val email: String) : SignInMainEvent()
    data class PasswordChanged(val password: String) : SignInMainEvent()
    data class VisiblePassword(val isVisiblePassword: Boolean) : SignInMainEvent()
    data object Submit : SignInMainEvent()
}
