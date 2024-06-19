package com.example.cryptocurrencycompose.crypto_listing.domain.repository

import com.example.cryptocurrencycompose.commons.util.Resource
import com.example.cryptocurrencycompose.crypto_listing.domain.model.CryptoModel
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {

    suspend fun getListings(
        fetchFromNetwork : Boolean,
        query : String
    ) : Flow<Resource<List<CryptoModel>>>

}