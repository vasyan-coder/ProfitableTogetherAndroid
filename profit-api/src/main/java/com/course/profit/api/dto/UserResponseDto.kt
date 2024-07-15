package com.course.profit.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    @SerialName("id") val id: Long,
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("username") val username: String,
    @SerialName("description") val description: String? = null,
    @SerialName("chatLink") val chatLink: String? = null
)
