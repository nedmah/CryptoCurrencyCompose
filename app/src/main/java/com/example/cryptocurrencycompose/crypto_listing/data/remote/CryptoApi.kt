package com.example.cryptocurrencycompose.crypto_listing.data.remote

import retrofit2.http.GET

interface CryptoApi {

    @GET("assets")
    suspend fun getCryptoListings() : CryptoDto


    companion object{
        const val BASE_URL = "https://api.coincap.io/v2/"
    }
}