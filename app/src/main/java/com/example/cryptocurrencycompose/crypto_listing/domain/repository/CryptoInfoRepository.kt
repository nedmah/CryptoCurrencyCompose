package com.example.cryptocurrencycompose.crypto_listing.domain.repository

import com.example.cryptocurrencycompose.commons.util.Resource
import com.example.cryptocurrencycompose.crypto_listing.domain.model.CryptoInfoModel

interface CryptoInfoRepository {

    suspend fun getCryptoInfo(id : String, interval : String) : Resource<List<CryptoInfoModel>>

}