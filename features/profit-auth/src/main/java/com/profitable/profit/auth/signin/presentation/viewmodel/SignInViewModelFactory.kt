package com.profitable.profit.auth.signin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.course.profit.data.ProfitRepository
import com.profitable.profit.auth.signin.domain.ValidateSignInEmailUseCase
import com.profitable.profit.auth.signin.domain.ValidateSignInPasswordUseCase

internal class SignInViewModelFactory(
    private val validateSignInEmailUseCase: ValidateSignInEmailUseCase,
    private val validateSignInPasswordUseCase: ValidateSignInPasswordUseCase,
    private val repository: ProfitRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInViewModel(
            validateSignInEmailUseCase = validateSignInEmailUseCase,
            validateSignInPasswordUseCase = validateSignInPasswordUseCase,
            repository = repository,
        ) as T
    }
}
