package com.profitable.profit.auth.aboutyourself.presentation.viewmodel.model

sealed class AboutMainEvent {
    data class TextChanged(val text: String) : AboutMainEvent()
    data class Save(val text: String) : AboutMainEvent()
    data class IsError(val isError: Boolean) : AboutMainEvent()
}
