package com.profitable.profit.auth.signin.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.profit.data.ProfitRepository
import com.course.profit.data.model.AuthRequest
import com.profitable.profit.auth.signin.domain.ValidateSignInEmailUseCase
import com.profitable.profit.auth.signin.domain.ValidateSignInPasswordUseCase
import com.profitable.profit.auth.signin.presentation.model.SignInStateUI
import com.profitable.profit.auth.signin.presentation.viewmodel.model.SignInMainEvent
import com.profitable.profit.uikit.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val validateSignInEmailUseCase: ValidateSignInEmailUseCase,
    private val validateSignInPasswordUseCase: ValidateSignInPasswordUseCase,
    private val repository: ProfitRepository,
) : ViewModel() {

    var formState by mutableStateOf(SignInStateUI())
        private set

    private val _isSuccessful = MutableStateFlow(false)
    val isSuccessful = _isSuccessful.asStateFlow()

    fun onEvent(event: SignInMainEvent) {
        when (event) {
            is SignInMainEvent.EmailChanged -> {
                formState = formState.copy(
                    email = event.email,
                    error = null,
                )
            }

            is SignInMainEvent.PasswordChanged -> {
                formState = formState.copy(
                    password = event.password,
                    error = null,
                )
            }

            SignInMainEvent.Submit -> {
                if (validateEmail() && validatePassword()) {
                    viewModelScope.launch {
                        val response = repository.login(
                            AuthRequest(
                                email = formState.email,
                                password = formState.password,
                            )
                        )
                        if (response.isSuccess) {
                            formState = formState.copy(
                                isSuccessful = true,
                            )
                            _isSuccessful.emit(true)
                        } else if (response.isFailure) {
                            formState = formState.copy(
                                error = response.exceptionOrNull()?.message?.let {
                                    UiText.DynamicString(
                                        "логин или пароль неверны"
                                    )
                                },
                            )
                        }
                    }
                }
            }

            is SignInMainEvent.VisiblePassword -> {
                formState = formState.copy(isVisiblePassword = event.isVisiblePassword)
            }
        }
    }

    private fun validatePassword(): Boolean {
        val passwordResult = validateSignInPasswordUseCase(formState.password)
        formState = formState.copy(error = passwordResult.errorMessage)
        return passwordResult.successful
    }

    private fun validateEmail(): Boolean {
        val emailResult = validateSignInEmailUseCase(formState.email)
        formState = formState.copy(error = emailResult.errorMessage)
        return emailResult.successful
    }
}
