package com.course.profit.data.model

data class UserRequest(
    val email: String,
    val password: String,
    val username: String,
    val description: String = "",
    val chatLink: String,
    val favourites: String = "",
)
