package com.example.cryptocurrencycompose.crypto_listing.data.util

import java.util.Calendar

class CryptoInfoFormatter {

    fun formatPrice(price: String): String {
        return String.format("%.3f", price.toDoubleOrNull() ?: 0.0)
    }
}