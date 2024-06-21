package com.example.cryptocurrencycompose.crypto_listing.data.remote

import com.squareup.moshi.Json

data class CryptoListingDataDto(

    val id : String,

    val symbol : String,

    val name : String,

    @field:Json(name = "priceUsd")
    val price : String,

    @field:Json(name = "changePercent24Hr")
    val percentage : String

)
