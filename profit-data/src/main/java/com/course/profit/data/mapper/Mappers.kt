package com.course.profit.data.mapper

import com.course.profit.api.dto.AuthRequestDto
import com.course.profit.api.dto.AuthResponseDto
import com.course.profit.api.dto.HomeDto
import com.course.profit.api.dto.UpdateChatLinkRequestDto
import com.course.profit.api.dto.UpdateDescriptionRequestDto
import com.course.profit.api.dto.UserInfoDto
import com.course.profit.api.dto.UserRequestDto
import com.course.profit.data.model.UpdateDescriptionRequest
import com.course.profit.data.model.AuthRequest
import com.course.profit.data.model.AuthResponse
import com.course.profit.data.model.Home
import com.course.profit.data.model.UpdateChatLinkRequest
import com.course.profit.data.model.UserInfo
import com.course.profit.data.model.UserRequest

fun UserRequest.toUserDto() = UserRequestDto(
    email = email,
    password = password,
    username = username,
    description = description,
    chatLink = chatLink,
    favourites = favourites,
)

fun AuthResponseDto.toAuthResponse() = AuthResponse(
    userId = userId,
    token = token,
    issuedAt = issuedAt,
    expiresAt = expiresAt,
)

fun AuthRequest.toAuthRequestDto() = AuthRequestDto(
    email = email,
    password = password,
)

fun UserInfoDto.toUserInfo() = UserInfo(
    id = id,
    email = email,
    username = username,
    description = description,
    chatLink = chatLink,
    favourites = favourites,
    reserved = reserved,
)

fun UpdateDescriptionRequest.toUpdateDescriptionRequestDto() = UpdateDescriptionRequestDto(
    description = description,
)

fun UpdateChatLinkRequest.toUpdateChatLinkRequestDto() = UpdateChatLinkRequestDto(
    chatLink = chatLink,
)

fun HomeDto.toHome() = Home(
    id = id,
    name = name,
    images = images,
    roomSizes = roomSizes,
    metroDistance = metroDistance,
    address = address,
    price = price,
    roommatesCount = roommatesCount,
    utilityBill = utilityBill,
    bail = bail,
    commissions = commissions,
    prepaymentPeriod = prepaymentPeriod,
    rentalPeriod = rentalPeriod,
    originalAnnounce = originalAnnounce,
    users = users,
)
