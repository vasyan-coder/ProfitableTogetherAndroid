package com.course.profit.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthRequestDto(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)
