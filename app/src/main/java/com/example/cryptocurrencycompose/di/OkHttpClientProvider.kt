package com.example.cryptocurrencycompose.di

import android.app.Application
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import javax.inject.Singleton

@Singleton
class OkHttpClientProvider (private val app : Application) {

    fun provideOkHttpClient(): OkHttpClient {
        // Размер кэша (5 MB)
        val cacheSize = 5 * 1024 * 1024 // 5 MB
        val cacheDir = File(app.cacheDir, "http_cache")
        val cache = Cache(cacheDir, cacheSize.toLong())

        // Настройка HttpLoggingInterceptor для логирования запросов и ответов
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // Настройка OkHttpClient с кэшем и логированием
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(logging)
            .addNetworkInterceptor { chain ->
                val response = chain.proceed(chain.request())
                // Устанавливаем время жизни кэша 5 минут (300 секунд)
                response.newBuilder()
                    .header("Cache-Control", "public, max-age=300")
                    .build()
            }
            .build()
    }
}