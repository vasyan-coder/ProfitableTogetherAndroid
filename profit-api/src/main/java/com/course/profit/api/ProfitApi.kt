package com.course.profit.api

import com.course.profit.api.dto.AuthRequestDto
import com.course.profit.api.dto.AuthResponseDto
import com.course.profit.api.dto.HomeDto
import com.course.profit.api.dto.UpdateChatLinkRequestDto
import com.course.profit.api.dto.UpdateDescriptionRequestDto
import com.course.profit.api.dto.UserInfoDto
import com.course.profit.api.dto.UserRequestDto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface ProfitApi {

    @POST("auth/register")
    suspend fun register(@Body userRequestDto: UserRequestDto): Result<AuthResponseDto>

    @POST("auth/login")
    suspend fun login(@Body authRequestDto: AuthRequestDto): Result<AuthResponseDto>

    @POST("auth/info")
    suspend fun getUserInfo(): Result<UserInfoDto>

    @POST("auth/description")
    suspend fun updateDescription(@Body description: UpdateDescriptionRequestDto)

    @POST("auth/chatlink")
    suspend fun updateChatLink(@Body chatLink: UpdateChatLinkRequestDto)

    @GET("auth/user")
    suspend fun getUser(@Query("userId") id: Long): Result<UserInfoDto>

    @GET("homes")
    suspend fun getHomes(
        @Query("minBudget") minBudget: Int? = null,
        @Query("maxBudget") maxBudget: Int? = null,
        @Query("roommatesCount") roommatesCount: Int? = null,
        @Query("metroDistances") metroDistances: String? = null,
    ): Result<List<HomeDto>>

    @POST("homes/favourites")
    suspend fun addFavourite(@Query("homeId") homeId: Long)

    @DELETE("homes/favourites")
    suspend fun removeFavourite(@Query("homeId") homeId: Long)

    @GET("homes/favourites")
    suspend fun getHomesByIds(): Result<List<HomeDto>>

    @GET("homes/home")
    suspend fun getHomeById(@Query("homeId") homeId: Long): Result<HomeDto>

    @POST("homes/reserve")
    suspend fun addReserve(@Query("homeId") homeId: Long)

    @DELETE("homes/reserve")
    suspend fun cancelReserve(@Query("homeId") homeId: Long)

    @PUT("homes/reserve")
    suspend fun getReserves(): Result<List<HomeDto>>
}

fun ProfitApi(
    baseUrl: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json
): ProfitApi {
    return retrofit(baseUrl, okHttpClient, json).create()
}

private fun retrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient?,
    json: Json
): Retrofit {
    val jsonConverterFactory = json.asConverterFactory("application/json".toMediaType())

    val modifiedOkHttpClient =
        (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
            .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(modifiedOkHttpClient)
        .build()
}
