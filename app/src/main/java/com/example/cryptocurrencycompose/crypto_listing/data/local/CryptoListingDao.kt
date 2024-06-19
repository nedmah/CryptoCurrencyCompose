package com.example.cryptocurrencycompose.crypto_listing.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface CryptoListingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListings(
        cryptoListingEntities : List<CryptoListingsEntity>
    )

    @Query("DELETE FROM cryptolistingsentity")
    suspend fun clearListings()

    @Query("""
            SELECT * 
            FROM cryptolistingsentity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                UPPER(:query) == symbol
        """)
    suspend fun searchForListings(query : String) : List<CryptoListingsEntity>
}