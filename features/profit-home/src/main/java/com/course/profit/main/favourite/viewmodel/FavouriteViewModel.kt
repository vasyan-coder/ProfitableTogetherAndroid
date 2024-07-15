package com.course.profit.main.favourite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.profit.data.ProfitRepository
import com.course.profit.data.model.Home
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val repository: ProfitRepository,
) : ViewModel() {

    private val _favouriteFlow: MutableStateFlow<FavouriteViewModelState> =
        MutableStateFlow(FavouriteViewModelState.None)
    val favouriteFlow = _favouriteFlow.asStateFlow()

    init {
        if (fetchToken()) {
            getUserInfo()
        } else {
            viewModelScope.launch {
                _favouriteFlow.emit(FavouriteViewModelState.Unauthorized)
            }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            val favourites = repository.getFavouriteHomes()
            val reserved = repository.getReserves()
            if (favourites.isSuccess && reserved.isSuccess) {
                _favouriteFlow.emit(
                    FavouriteViewModelState.Success(
                        favourites.getOrNull() ?: emptyList(),
                        reserved.getOrNull() ?: emptyList(),
                    )
                )
            }
        }
    }

    private fun fetchToken(): Boolean {
        return repository.fetchToken() != null
    }

    fun removeFavourite(homeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFavourite(homeId)
            getUserInfo()
        }
    }

    fun addFavourite(homeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavourite(homeId)
            getUserInfo()
        }
    }
}

sealed class FavouriteViewModelState {
    data object None : FavouriteViewModelState()
    data object Loading : FavouriteViewModelState()
    data class Success(val favouriteList: List<Home>, val reservedList: List<Home>) :
        FavouriteViewModelState()

    data class Error(val error: String) : FavouriteViewModelState()
    data object Unauthorized : FavouriteViewModelState()
}
