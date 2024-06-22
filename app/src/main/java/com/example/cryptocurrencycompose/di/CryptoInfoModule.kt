package com.example.cryptocurrencycompose.di

import com.example.cryptocurrencycompose.crypto_listing.data.repository.CryptoInfoRepositoryImpl
import com.example.cryptocurrencycompose.crypto_listing.data.repository.CryptoRepositoryImpl
import com.example.cryptocurrencycompose.crypto_listing.domain.repository.CryptoInfoRepository
import com.example.cryptocurrencycompose.crypto_listing.domain.repository.CryptoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptoInfoModule {


    @Binds
    @Singleton
    abstract fun bindCryptoInfoRepository(repositoryImpl: CryptoInfoRepositoryImpl) : CryptoInfoRepository
}