package com.profitable.together.di

import android.content.Context
import com.course.profit.api.ProfitApi
import com.course.profit.data.SessionManager
import com.profitable.together.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfitModule {

    @Provides
    @Singleton
    fun provideProfitApi(
        okHttpClient: OkHttpClient?,
    ): ProfitApi {
        return ProfitApi(
            baseUrl = BuildConfig.PROFIT_API_BASE_URL,
            okHttpClient = okHttpClient,
        )
    }

    @Provides
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }
}
