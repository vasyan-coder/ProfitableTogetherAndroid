package com.course.profit.data.model

data class UserResponse(
    val id: Long,
    val email: String,
    val password: String,
    val username: String,
    val description: String? = null,
    val chatLink: String? = null
)
