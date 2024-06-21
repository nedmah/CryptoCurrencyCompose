package com.example.cryptocurrencycompose.crypto_listing.data.remote

import com.squareup.moshi.Json

data class CryptoInfoDataDto(

    @field:Json(name = "priceUsd")
    val price : String,

    val time : Long,

    val date : String
)
