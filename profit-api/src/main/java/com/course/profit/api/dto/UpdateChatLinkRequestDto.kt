package com.course.profit.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateChatLinkRequestDto(
    @SerialName("chat_link") val chatLink: String,
)
