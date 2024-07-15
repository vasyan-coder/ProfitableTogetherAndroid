package com.course.profit.main.userprofile.presentation.viewmodel

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
class UserProfileViewModel @Inject constructor(
    private val repository: ProfitRepository,
) : ViewModel() {

    private val _user = MutableStateFlow<UserProfileState>(UserProfileState.Loading)
    val user = _user.asStateFlow()

    fun getUser(userId: Long) {
        viewModelScope.launch {
            val response = repository.getUser(userId)
            _user.emit(UserProfileState.Success(response.getOrNull() ?: return@launch))
        }
    }
}

sealed class UserProfileState {
    data object Loading : UserProfileState()
    data class Success(val user: UserInfo) : UserProfileState()
}
