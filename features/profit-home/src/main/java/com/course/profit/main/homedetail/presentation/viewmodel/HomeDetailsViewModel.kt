package com.course.profit.main.homedetail.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.profit.data.ProfitRepository
import com.course.profit.data.model.Home
import com.profitable.profit.uikit.components.buttons.ProfitReserveButtonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeDetailsViewModel @Inject constructor(
    private val repository: ProfitRepository,
) : ViewModel() {

    var unauthorizedState by mutableStateOf(false)
        private set

    init {
        if (repository.fetchToken() == null) {
            unauthorizedState = true
        }
    }

    var reservationState by mutableStateOf<ProfitReserveButtonState>(ProfitReserveButtonState.NotReserved)
        private set

    private val _homeDetailsState =
        MutableStateFlow<HomeDetailViewModelState>(HomeDetailViewModelState.Loading)
    val homeDetailsState = _homeDetailsState.asStateFlow()

    fun getHomeById(homeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getHomeById(homeId)
            val user = repository.getUserInfo()
            val reserves: Result<List<Home>> = repository.getReserves()
            val favourites = user.getOrNull()?.favourites?.split(",") ?: emptyList()

            val usernamesString = data.getOrNull()?.users ?: ""
            val usernames = mutableListOf<Pair<Long, String>>()
            if (usernamesString.isNotBlank()) {
                if (!usernamesString.any { it == ',' }) {
                    if (usernamesString.toLong() != (user.getOrNull()?.id ?: 0L)) {
                        usernames.add(
                            Pair(
                                usernamesString.toLong(),
                                repository.getUser(usernamesString.toLong()).getOrNull()?.username
                                    ?: ""
                            )
                        )
                    }
                } else {
                    val t = usernamesString.split(",").map { it.toLong() }
                    for (el in t) {
                        if (el != (user.getOrNull()?.id ?: 0L)) {
                            usernames.add(
                                Pair(
                                    el,
                                    repository.getUser(el).getOrNull()?.username ?: ""
                                )
                            )
                        }
                    }
                }
            }


            if (data.getOrNull() != null) {
                _homeDetailsState.emit(
                    HomeDetailViewModelState.Success(
                        data.getOrNull()!!,
                        favourites,
                        reserves.getOrNull() ?: emptyList(),
                        reserves.getOrNull()?.any { it.id == homeId } ?: false,
                        usernames,
                    )
                )
            }
        }
    }

    fun addFavourite(homeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavourite(homeId)
            getHomeById(homeId)
        }
    }

    fun removeFavourite(homeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFavourite(homeId)
            getHomeById(homeId)
        }
    }

    fun addReverse(homeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            reservationState = ProfitReserveButtonState.Loading
            delay(3000L)
            repository.addReserve(homeId)
            getHomeById(homeId)
            reservationState = ProfitReserveButtonState.Reserved
        }
    }

    fun cancelReverse(homeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.cancelReserve(homeId)
            getHomeById(homeId)
            reservationState = ProfitReserveButtonState.NotReserved
        }
    }
}

sealed class HomeDetailViewModelState {
    data object Loading : HomeDetailViewModelState()
    data class Success(
        val data: Home,
        val favourites: List<String>,
        val reserves: List<Home>,
        val isReserves: Boolean,
        val usernames: List<Pair<Long, String>>,
    ) : HomeDetailViewModelState()

    data object Unauthorized : HomeDetailViewModelState()
}
