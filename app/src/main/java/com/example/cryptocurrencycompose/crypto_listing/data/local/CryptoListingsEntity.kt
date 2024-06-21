package com.example.cryptocurrencycompose.crypto_listing.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CryptoListingsEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val cryptoId : String,
    val symbol : String,
    val name : String,
    val price : String,
    val percentage : String
)