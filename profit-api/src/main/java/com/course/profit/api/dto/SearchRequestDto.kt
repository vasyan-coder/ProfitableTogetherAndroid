package com.course.profit.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A serializable data class representing a search request DTO.
 *
 * @property minBudget The minimum budget for the search.
 * @property maxBudget The maximum budget for the search.
 * @property roommatesQuantity The quantity of roommates desired.
 * @property metroStations The list of metro stations desired.
 */
@Serializable
data class SearchRequestDto(
    @SerialName("min_budget") val minBudget: Int,
    @SerialName("max_budget") val maxBudget: Int,
    @SerialName("roommates_quantity") val roommatesQuantity: Int,
    @SerialName("metro_stations") val metroStations: List<String>,
)
