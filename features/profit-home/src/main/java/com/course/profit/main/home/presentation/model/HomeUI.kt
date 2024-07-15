package com.course.profit.main.home.presentation.model

data class HomeUI(
    val id: Long,
    val imageLink: String,
    val title: String,
    val roomSizes: String,
    val address: String,
    val price: String,
    val isFavourite: Boolean,
)
