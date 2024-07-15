package com.course.profit.main.home.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
class HomeViewModel @Inject constructor(
    private val repository: ProfitRepository,
) : ViewModel() {

    var unauthorizedState by mutableStateOf(false)
        private set

    init {
        if (repository.fetchToken() == null) {
            unauthorizedState = true
        }
    }

    private val _homes =
        MutableStateFlow<HomeState>(HomeState.Loading)
    val homes = _homes.asStateFlow()

    var homeUIState by mutableStateOf(HomeStateUI())

    fun getHomes() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getHomes(
                minBudget = homeUIState.minBudget,
                maxBudget = homeUIState.maxBudget,
                roommatesCount = homeUIState.roommatesCount,
                metroDistances = homeUIState.metroDistances,
            ).getOrNull() ?: emptyList()

            val favorites = repository.getFavouriteHomes().getOrNull() ?: emptyList()

            _homes.emit(HomeState.Success(response, favorites))
        }
    }

    fun setSearchParams(
        minBudget: Int? = null,
        maxBudget: Int? = null,
        roommatesCount: Int? = null,
        metroDistances: String? = null,
    ) {
        homeUIState = homeUIState.copy(
            minBudget = minBudget,
            maxBudget = maxBudget,
            roommatesCount = roommatesCount,
            metroDistances = metroDistances,
        )
    }

    fun addFavourite(homeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavourite(homeId)
            getHomes()
        }
    }

    fun removeFavourite(homeId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFavourite(homeId)
            getHomes()
        }
    }
}

sealed class HomeState {
    data object Loading : HomeState()
    data class Error(val message: String) : HomeState()
    data class Success(val homes: List<Home>, val favourites: List<Home>) : HomeState()
}

data class HomeStateUI(
    val minBudget: Int? = null,
    val maxBudget: Int? = null,
    val roommatesCount: Int? = null,
    val metroDistances: String? = null,
)
