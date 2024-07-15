package com.course.profit.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateDescriptionRequestDto(
    @SerialName("description") val description: String,
)
