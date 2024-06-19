package com.example.cryptocurrencycompose.crypto_listing.presentation

sealed class CryptoListingsEvents {
    data class OnSearchQueryChange(val query : String) : CryptoListingsEvents()
    object Refresh : CryptoListingsEvents()
}