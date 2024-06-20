package com.example.cryptocurrencycompose.di

import com.example.cryptocurrencycompose.crypto_listing.data.repository.CryptoRepositoryImpl
import com.example.cryptocurrencycompose.crypto_listing.domain.repository.CryptoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptoListingsModule {


    @Binds
    @Singleton
    abstract fun bindCryptoListingsRepository(repositoryImpl: CryptoRepositoryImpl) : CryptoRepository
}