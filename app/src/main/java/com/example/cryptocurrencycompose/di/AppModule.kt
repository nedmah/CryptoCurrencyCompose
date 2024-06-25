package com.example.cryptocurrencycompose.di

import android.app.Application
import androidx.room.Room
import com.example.cryptocurrencycompose.crypto_listing.data.local.CryptoDb
import com.example.cryptocurrencycompose.crypto_listing.data.remote.CryptoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.File
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDb(app: Application) : CryptoDb{
        return Room.databaseBuilder(app, CryptoDb::class.java,"cryptoDb.db").build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClientProvider(app: Application): OkHttpClientProvider {
        return OkHttpClientProvider(app)
    }


    @Provides
    @Singleton
    fun provideCryptoApi(okHttpClientProvider: OkHttpClientProvider) : CryptoApi{

        return Retrofit.Builder()
            .baseUrl(CryptoApi.BASE_URL)
            .client(okHttpClientProvider.provideOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }



}