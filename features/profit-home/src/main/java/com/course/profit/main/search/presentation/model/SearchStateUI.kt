package com.course.profit.main.search.presentation.model

data class SearchStateUI(
    val minBudget: String = "",
    val isErrorMinBudget: Boolean = false,
    val maxBudget: String = "",
    val isErrorMaxBudget: Boolean = false,
    val roommatesQuantity: String = "",
    val isErrorRoommatesQuantity: Boolean = false,
    val metroStation: String = "",
)
