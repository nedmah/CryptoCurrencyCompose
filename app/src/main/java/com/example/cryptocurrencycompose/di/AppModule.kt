package com.example.cryptocurrencycompose.di

import android.app.Application
import androidx.room.Room
import com.example.cryptocurrencycompose.crypto_listing.data.local.CryptoDb
import com.example.cryptocurrencycompose.crypto_listing.data.remote.CryptoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
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
    fun provideCryptoApi() : CryptoApi{
        return Retrofit.Builder()
            .baseUrl(CryptoApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
}