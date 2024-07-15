package com.course.profit.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A serializable data class representing a response DTO for Profit-related operations.
 *
 * @property success Indicates whether the operation was successful.
 * @property message A message accompanying the response.
 */
@Serializable
data class ProfitResponseDto(
    @SerialName("success") val success: Boolean,
    @SerialName("message") val message: String,
)
