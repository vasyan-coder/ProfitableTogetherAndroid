package com.course.profit.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoDto(
    @SerialName("id") val id: Long,
    @SerialName("email") val email: String,
    @SerialName("username") val username: String,
    @SerialName("description") val description: String,
    @SerialName("chat_link") val chatLink: String,
    @SerialName("favourites") val favourites: String,
    @SerialName("reserved") val reserved: String,
)
