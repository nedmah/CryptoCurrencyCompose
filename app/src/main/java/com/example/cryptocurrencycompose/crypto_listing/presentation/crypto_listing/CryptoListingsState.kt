package com.example.cryptocurrencycompose.crypto_listing.presentation.crypto_listing

import com.example.cryptocurrencycompose.crypto_listing.domain.model.CryptoModel

data class CryptoListingsState(
    val isLoading : Boolean = false,
    val isRefreshing : Boolean = false,
    val error : String = "",
    val cryptos : List<CryptoModel> = emptyList(),
    val searchQuery : String = ""
)
