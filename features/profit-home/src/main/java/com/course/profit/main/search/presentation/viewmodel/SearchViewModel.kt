package com.course.profit.main.search.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.course.profit.main.search.presentation.model.SearchStateUI
import com.course.profit.main.search.presentation.viewmodel.model.SearchMainEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    private val _isSuccessfulSearch = MutableStateFlow(false)
    val isSuccessfulSearch = _isSuccessfulSearch.asStateFlow()

    var searchStateUI by mutableStateOf(SearchStateUI())
        private set

    fun onEvent(event: SearchMainEvent) {
        when (event) {
            is SearchMainEvent.MaxBudgetChanged -> {
                searchStateUI = searchStateUI.copy(
                    maxBudget = event.maxBudget,
                    isErrorMinBudget = false,
                    isErrorMaxBudget = false,
                )
            }

            is SearchMainEvent.MetroStationChanged -> {
                searchStateUI = searchStateUI.copy(
                    metroStation = event.metroStation,
                )
            }

            is SearchMainEvent.MinBudgetChanged -> {
                searchStateUI = searchStateUI.copy(
                    minBudget = event.minBudget,
                    isErrorMinBudget = false,
                    isErrorMaxBudget = false,
                )
            }

            is SearchMainEvent.RoommatesQuantityChanged -> {
                searchStateUI = searchStateUI.copy(
                    roommatesQuantity = event.roommatesQuantity,
                    isErrorRoommatesQuantity = false,
                )
            }

            SearchMainEvent.Submit -> {
                validateSearchFields()
                if (
                    !searchStateUI.isErrorRoommatesQuantity &&
                    !searchStateUI.isErrorMaxBudget &&
                    !searchStateUI.isErrorMinBudget &&
                    (searchStateUI.minBudget.toInt() < searchStateUI.maxBudget.toInt())
                ) {
                    _isSuccessfulSearch.value = true
                }
            }
        }
    }

    private fun validateSearchFields() {
        if (!searchStateUI.minBudget.isDigitsOnly() || searchStateUI.minBudget.isBlank()) {
            searchStateUI = searchStateUI.copy(
                isErrorMinBudget = true,
            )
        }

        if (!searchStateUI.maxBudget.isDigitsOnly() || searchStateUI.maxBudget.isBlank()) {
            searchStateUI = searchStateUI.copy(
                isErrorMaxBudget = true,
            )
        }

        if (!searchStateUI.roommatesQuantity.isDigitsOnly() || searchStateUI.roommatesQuantity.isBlank()) {
            searchStateUI = searchStateUI.copy(
                isErrorRoommatesQuantity = true,
            )
        }

        if (searchStateUI.minBudget.isNotEmpty() && searchStateUI.maxBudget.isNotEmpty()) {
            if (searchStateUI.minBudget.toInt() > searchStateUI.maxBudget.toInt()) {
                searchStateUI = searchStateUI.copy(
                    isErrorMinBudget = true,
                    isErrorMaxBudget = true,
                )
            }
        }
    }
}
