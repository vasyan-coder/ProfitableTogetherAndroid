package com.course.profit.data.model

import java.util.Date

data class AuthResponse(
    val userId: Long,
    val token: String,
    val issuedAt: Date,
    val expiresAt: Date,
)
