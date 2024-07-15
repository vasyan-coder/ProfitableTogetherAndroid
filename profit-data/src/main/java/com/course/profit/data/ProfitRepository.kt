package com.course.profit.data

import com.course.profit.api.ProfitApi
import com.course.profit.data.mapper.toAuthRequestDto
import com.course.profit.data.mapper.toAuthResponse
import com.course.profit.data.mapper.toHome
import com.course.profit.data.mapper.toUpdateChatLinkRequestDto
import com.course.profit.data.mapper.toUpdateDescriptionRequestDto
import com.course.profit.data.mapper.toUserDto
import com.course.profit.data.mapper.toUserInfo
import com.course.profit.data.model.AuthRequest
import com.course.profit.data.model.AuthResponse
import com.course.profit.data.model.Home
import com.course.profit.data.model.UpdateChatLinkRequest
import com.course.profit.data.model.UpdateDescriptionRequest
import com.course.profit.data.model.UserInfo
import com.course.profit.data.model.UserRequest
import javax.inject.Inject

class ProfitRepository @Inject constructor(
    private val api: ProfitApi,
    private val sessionManager: SessionManager,
) {

    suspend fun register(userRequest: UserRequest): Result<AuthResponse> {
        return try {
            val response = api.register(userRequest.toUserDto())
            if (response.isSuccess) {
                val data = response.getOrThrow()
                sessionManager.saveAuthToken(data.token)
                Result.success(response.getOrThrow().toAuthResponse())
            } else {
                Result.failure(RuntimeException("Unknown error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(authRequest: AuthRequest): Result<AuthResponse> {
        return try {
            val response = api.login(authRequest.toAuthRequestDto())
            if (response.isSuccess) {
                val data = response.getOrThrow()
                sessionManager.saveAuthToken(data.token)
                Result.success(response.getOrThrow().toAuthResponse())
            } else {
                Result.failure(RuntimeException("Unknown error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUserInfo(): Result<UserInfo> {
        return try {
            val response = api.getUserInfo()
            Result.success(response.getOrThrow().toUserInfo())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateDescription(description: UpdateDescriptionRequest) {
        try {
            api.updateDescription(description.toUpdateDescriptionRequestDto())
        } catch (_: Exception) {
        }
    }

    suspend fun updateChatLink(chatLink: UpdateChatLinkRequest) {
        try {
            api.updateChatLink(chatLink.toUpdateChatLinkRequestDto())
        } catch (_: Exception) {
        }
    }

    suspend fun getUser(userId: Long): Result<UserInfo> {
        return try {
            val response = api.getUser(userId)
            if (response.isSuccess) {
                Result.success(response.getOrThrow().toUserInfo())
            } else {
                Result.failure(RuntimeException("Unknown error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getHomes(
        minBudget: Int? = null,
        maxBudget: Int? = null,
        roommatesCount: Int? = null,
        metroDistances: String? = null,
    ): Result<List<Home>> {
        return try {
            val response = api.getHomes(
                minBudget = minBudget,
                maxBudget = maxBudget,
                roommatesCount = roommatesCount,
                metroDistances = metroDistances,
            )
            if (response.isSuccess) {
                Result.success(response.getOrThrow().map { it.toHome() })
            } else {
                Result.failure(RuntimeException("Unknown error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun addFavourite(homeId: Long) {
        try {
            api.addFavourite(homeId)
        } catch (_: Exception) {
        }
    }

    suspend fun removeFavourite(homeId: Long) {
        try {
            api.removeFavourite(homeId)
        } catch (_: Exception) {
        }
    }

    suspend fun getFavouriteHomes(): Result<List<Home>> {
        return try {
            val response = api.getHomesByIds()
            if (response.isSuccess) {
                Result.success(response.getOrThrow().map { it.toHome() })
            } else {
                Result.failure(RuntimeException("Unknown error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getHomeById(homeId: Long): Result<Home> {
        return try {
            val response = api.getHomeById(homeId)
            if (response.isSuccess) {
                Result.success(response.getOrThrow().toHome())
            } else {
                Result.failure(RuntimeException("Unknown error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun addReserve(homeId: Long) {
        try {
            api.addReserve(homeId)
        } catch (_: Exception) {
        }
    }

    suspend fun cancelReserve(homeId: Long) {
        try {
            api.cancelReserve(homeId)
        } catch (_: Exception) {
        }
    }

    suspend fun getReserves(): Result<List<Home>> {
        return try {
            val response = api.getReserves()
            if (response.isSuccess) {
                Result.success(response.getOrThrow().map { it.toHome() })
            } else {
                Result.failure(RuntimeException("Unknown error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun removeToken() {
        sessionManager.removeAuthToken()
    }

    fun fetchToken(): String? {
        return sessionManager.fetchAuthToken()
    }
}
