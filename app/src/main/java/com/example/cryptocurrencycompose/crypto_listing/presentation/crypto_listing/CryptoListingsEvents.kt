package com.example.cryptocurrencycompose.crypto_listing.presentation.crypto_listing

sealed class CryptoListingsEvents {
    data class OnSearchQueryChange(val query : String) : CryptoListingsEvents()
    object Refresh : CryptoListingsEvents()
}