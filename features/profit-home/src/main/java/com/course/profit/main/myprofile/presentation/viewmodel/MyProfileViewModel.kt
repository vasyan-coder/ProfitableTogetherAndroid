package com.course.profit.main.myprofile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.profit.data.ProfitRepository
import com.course.profit.data.model.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val repository: ProfitRepository,
) : ViewModel() {

    private val _userFlow: MutableStateFlow<MyProfileState> = MutableStateFlow(MyProfileState.None)
    val userFlow = _userFlow.asStateFlow()

    init {
        if (fetchToken()) {
            getUserInfo()
        } else {
            viewModelScope.launch {
                _userFlow.emit(MyProfileState.Unauthorized)
            }
        }
    }

    fun removeToken() {
        repository.removeToken()
    }

    private fun fetchToken(): Boolean {
        return repository.fetchToken() != null
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            _userFlow.emit(MyProfileState.Loading)
            val user = repository.getUserInfo()
            if (user.isSuccess) {
                _userFlow.emit(MyProfileState.Success(user.getOrNull()))
            }
        }
    }
}

sealed class MyProfileState {
    data object None : MyProfileState()
    data object Loading : MyProfileState()
    data class Success(val userInfo: UserInfo?) : MyProfileState()
    data class Error(val error: String) : MyProfileState()
    data object Unauthorized : MyProfileState()
}
