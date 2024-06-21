package com.example.cryptocurrencycompose.crypto_listing.data.util

import java.util.Calendar

class CryptoInfoFormatter {

    fun getHourFromTimestamp(timestamp: Long): Int {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        return calendar.get(Calendar.HOUR_OF_DAY)
    }

    fun formatPrice(price: String): String {
        return String.format("%.3f", price.toDoubleOrNull() ?: 0.0)
    }
}