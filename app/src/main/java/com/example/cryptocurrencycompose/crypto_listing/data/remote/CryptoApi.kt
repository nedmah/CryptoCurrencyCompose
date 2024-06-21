package com.example.cryptocurrencycompose.crypto_listing.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApi {

    @GET("assets")
    suspend fun getCryptoListings() : CryptoListingDto


    /**
     * m1 - price every minute in  day  (should probably take every 60 element in 1440 of all = 24)
     * m30 - every 30 minutes in 14 days (every 48 in 672 = 14)
     * h1 - every hour in 1 month (every 24 in 720 = 30)
     */
    @GET("assets/{id}/history")
    suspend fun getCryptoInfo(
        @Path("id") id: String,
        @Query("interval") interval : String
    ) : CryptoInfoDto


    companion object{
        const val BASE_URL = "https://api.coincap.io/v2/"
    }
}