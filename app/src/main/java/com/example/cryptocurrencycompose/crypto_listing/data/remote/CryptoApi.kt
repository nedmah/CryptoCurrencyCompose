package com.example.cryptocurrencycompose.crypto_listing.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApi {

    @GET("assets")
    suspend fun getCryptoListings() : CryptoListingDto


    /** intervals:
     * h1 - every hour in 1 month (every 24 in 720 = 30)
     * d1 - every day in 1 year
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