package com.course.profit.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A serializable data class representing a DTO for changing user information.
 *
 * @property photoUri The URI of the user's photo.
 * @property name The name of the user.
 * @property description The description or bio of the user.
 * @property chatLink The chat link associated with the user.
 */
@Serializable
data class ChangeUserInfoDto(
    @SerialName("photo_uri") val photoUri: String,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("chatLink") val chatLink: String,
)
