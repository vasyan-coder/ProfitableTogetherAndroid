package com.course.profit.main.homedetail.presentation.model

data class HomeDetailsUI(
    val id: Long,
    val images: List<String>,
    val roomSizes: String,
    val metroDistance: List<String>,
    val address: String,
    val price: String,
    val utilityBill: String,
    val bail: String,
    val commissions: String,
    val prepaymentPeriod: String,
    val rentalPeriod: String,
    val originalAnnounce: String = "",
    val users: List<String> = emptyList(),
    val isFavourite: Boolean,
    val isReserved: Boolean,
)
