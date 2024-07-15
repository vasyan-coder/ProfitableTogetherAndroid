package com.course.profit.api.dto

import com.course.profit.api.utils.DateTimeUTCSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class AuthResponseDto(
    @SerialName("user_id") val userId: Long,
    @SerialName("token") val token: String,
    @[SerialName("issued_at") Serializable(DateTimeUTCSerializer::class)] val issuedAt: Date,
    @[SerialName("expires_at") Serializable(DateTimeUTCSerializer::class)] val expiresAt: Date,
)
