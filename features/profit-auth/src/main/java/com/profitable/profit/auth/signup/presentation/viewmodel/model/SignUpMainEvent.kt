package com.profitable.profit.auth.signup.presentation.viewmodel.model

sealed class SignUpMainEvent {
    data class NameChanged(val name: String) : SignUpMainEvent()
    data class EmailChanged(val email: String) : SignUpMainEvent()
    data class PasswordChanged(val password: String) : SignUpMainEvent()
    data class ChatLinkChanged(val chatLink: String) : SignUpMainEvent()
    data class VisiblePassword(val isVisiblePassword: Boolean) : SignUpMainEvent()
    data object Submit : SignUpMainEvent()
}
