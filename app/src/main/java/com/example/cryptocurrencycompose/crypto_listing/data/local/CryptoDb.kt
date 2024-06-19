package com.example.cryptocurrencycompose.crypto_listing.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        CryptoListingsEntity::class
    ],
    version = 1
)
abstract class CryptoDb : RoomDatabase() {

    abstract fun getCryptoListingDao() : CryptoListingDao
}