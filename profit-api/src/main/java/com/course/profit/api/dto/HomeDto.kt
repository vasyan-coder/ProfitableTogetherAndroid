package com.course.profit.api.dto

/**
 * A serializable data class representing a home DTO.
 *
 * @property id The ID of the home.
 * @property imageLink The link to the image associated with the home.
 * @property title The title or name of the home.
 * @property roomSizes Information about the sizes of rooms in the home.
 * @property address The address of the home.
 * @property price The price of the home.
 * @property isFavourite Indicates whether the home is marked as a favorite.
 */
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("images") val images: String,
    @SerialName("room_sizes") val roomSizes: String,
    @SerialName("metro_distance") val metroDistance: String,
    @SerialName("address") val address: String,
    @SerialName("price") val price: Int,
    @SerialName("roommates_count") val roommatesCount: Int,
    @SerialName("utility_bill") val utilityBill: String,
    @SerialName("bail") val bail: String,
    @SerialName("commissions") val commissions: String,
    @SerialName("prepayment_period") val prepaymentPeriod: String,
    @SerialName("rental_period") val rentalPeriod: String,
    @SerialName("original_announce") val originalAnnounce: String,
    @SerialName("users") val users: String,
)
