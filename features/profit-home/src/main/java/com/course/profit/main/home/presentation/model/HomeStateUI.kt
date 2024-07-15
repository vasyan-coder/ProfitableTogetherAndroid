package com.course.profit.main.home.presentation.model

import com.course.profit.data.model.Home

data class HomeStateUI(
    val isLoading: Boolean = true,
    val homes: List<Home> = emptyList(),
    val favourites: List<String> = emptyList(),
)
