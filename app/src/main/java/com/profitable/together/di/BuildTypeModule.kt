package com.profitable.together.di

import android.content.Context
import com.course.profit.data.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BuildTypeModule {
    @Provides
    @Singleton
    fun provideHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val logging =
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(AuthInterceptor(context))
            .build()
    }
}
