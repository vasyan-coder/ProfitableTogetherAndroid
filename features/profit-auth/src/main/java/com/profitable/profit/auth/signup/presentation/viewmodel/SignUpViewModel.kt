package com.profitable.profit.auth.signup.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.profit.data.ProfitRepository
import com.course.profit.data.model.UserRequest
import com.profitable.profit.auth.signup.domain.ValidateSignUpEmailUseCase
import com.profitable.profit.auth.signup.domain.ValidateSignUpPasswordUseCase
import com.profitable.profit.auth.signup.domain.ValidateSignUpUsernameUseCase
import com.profitable.profit.auth.signup.presentation.model.SignUpStateUI
import com.profitable.profit.auth.signup.presentation.viewmodel.model.SignUpMainEvent
import com.profitable.profit.uikit.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validateSignUpUsernameUseCase: ValidateSignUpUsernameUseCase,
    private val validateSignUpEmailUseCase: ValidateSignUpEmailUseCase,
    private val validateSignUpPasswordUseCase: ValidateSignUpPasswordUseCase,
    private val repository: ProfitRepository,
) : ViewModel() {

    private val _isRegistered = MutableSharedFlow<Boolean>(1)
    val isRegistered = _isRegistered.asSharedFlow()

    var formState by mutableStateOf(SignUpStateUI())
        private set

    fun onEvent(event: SignUpMainEvent) {
        when (event) {
            is SignUpMainEvent.NameChanged -> {
                formState = formState.copy(
                    name = event.name,
                    error = null,
                )
            }

            is SignUpMainEvent.EmailChanged -> {
                formState = formState.copy(
                    email = event.email,
                    error = null,
                )
            }

            is SignUpMainEvent.PasswordChanged -> {
                formState = formState.copy(
                    password = event.password,
                    error = null,
                )
            }

            is SignUpMainEvent.ChatLinkChanged -> {
                formState = formState.copy(
                    chatLink = event.chatLink,
                    error = null,
                )
            }

            SignUpMainEvent.Submit -> {
                if (validateUsername() && validateEmail() && validatePassword() && validateChatLink()) {
                    viewModelScope.launch {
                        val result = repository.register(
                            UserRequest(
                                username = formState.name,
                                password = formState.password,
                                email = formState.email,
                                chatLink = formState.chatLink,
                            )
                        )
                        if (result.isSuccess) {
                            _isRegistered.emit(true)
                        }
                    }
                }
            }

            is SignUpMainEvent.VisiblePassword -> {
                formState = formState.copy(isVisiblePassword = event.isVisiblePassword)
            }

        }
    }

    private fun validateUsername(): Boolean {
        val usernameResult = validateSignUpUsernameUseCase(formState.name)
        formState = formState.copy(error = usernameResult.errorMessage)
        return usernameResult.successful
    }

    private fun validatePassword(): Boolean {
        val passwordResult = validateSignUpPasswordUseCase(formState.password)
        formState = formState.copy(error = passwordResult.errorMessage)
        return passwordResult.successful
    }

    private fun validateEmail(): Boolean {
        val emailResult = validateSignUpEmailUseCase(formState.email)
        formState = formState.copy(error = emailResult.errorMessage)
        return emailResult.successful
    }

    private fun validateChatLink(): Boolean {
        val chatLinkResult = formState.chatLink.isBlank()
        if (chatLinkResult) {
            formState = formState.copy(error = UiText.DynamicString("ссылка некорректная"))
            return false
        } else {
            return true
        }
    }

}
