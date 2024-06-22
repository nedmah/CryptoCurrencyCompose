package com.example.cryptocurrencycompose.crypto_listing.presentation.crypto_chart

sealed class CryptoInfoEvents {
    object YearButton : CryptoInfoEvents()
    object MonthButton : CryptoInfoEvents()
    object WeekButton : CryptoInfoEvents()
    object DayButton : CryptoInfoEvents()
}