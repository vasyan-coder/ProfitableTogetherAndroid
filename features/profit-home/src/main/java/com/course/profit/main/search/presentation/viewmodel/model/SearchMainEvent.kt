package com.course.profit.main.search.presentation.viewmodel.model

sealed class SearchMainEvent {
    data class MinBudgetChanged(val minBudget: String) : SearchMainEvent()
    data class MaxBudgetChanged(val maxBudget: String) : SearchMainEvent()
    data class RoommatesQuantityChanged(val roommatesQuantity: String) : SearchMainEvent()
    data class MetroStationChanged(val metroStation: String) : SearchMainEvent()
    data object Submit : SearchMainEvent()
}
