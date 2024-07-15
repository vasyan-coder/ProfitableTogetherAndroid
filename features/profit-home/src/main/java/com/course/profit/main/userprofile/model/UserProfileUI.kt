package com.course.profit.main.userprofile.model

import androidx.compose.runtime.Immutable

@Immutable
data class UserProfileUI(
    val id: Long,
    val photoUri: String,
    val name: String,
    val description: String,
    val chatLink: String,
)
