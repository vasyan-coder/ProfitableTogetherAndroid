package com.course.profit.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A serializable data class representing details of a home.
 *
 * @property id The ID of the home.
 * @property images The list of image URLs associated with the home.
 * @property roomSizes Information about the sizes of rooms in the home.
 * @property metroDistance The distance to the nearest metro stations.
 * @property address The address of the home.
 * @property price The price of the home.
 * @property utilityBill Information about utility bills associated with the home.
 * @property bail The bail amount for the home.
 * @property commissions Information about commissions for renting or buying the home.
 * @property prepaymentPeriod The prepayment period for renting the home.
 * @property rentalPeriod The rental period for the home.
 * @property originalAnnounce The original announcement of the home.
 * @property users The list of user IDs associated with the home.
 * @property isFavourite Indicates whether the home is marked as a favorite.
 * @property isReserved Indicates whether the home is reserved.
 */
@Serializable
data class HomeDetailsDto(
    @SerialName("id") val id: Long,
    @SerialName("images") val images: List<String>,
    @SerialName("room_sizes") val roomSizes: String,
    @SerialName("metro_distance") val metroDistance: List<String>,
    @SerialName("address") val address: String,
    @SerialName("price") val price: String,
    @SerialName("utility_bill") val utilityBill: String,
    @SerialName("bail") val bail: String,
    @SerialName("commissions") val commissions: String,
    @SerialName("prepayment_period") val prepaymentPeriod: String,
    @SerialName("rental_period") val rentalPeriod: String,
    @SerialName("original_announce") val originalAnnounce: String = "",
    @SerialName("users") val users: List<String> = emptyList(),
    @SerialName("is_favourite") val isFavourite: Boolean,
    @SerialName("is_reserved") val isReserved: Boolean,
)
