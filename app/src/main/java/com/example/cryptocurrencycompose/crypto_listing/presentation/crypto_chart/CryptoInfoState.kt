package com.example.cryptocurrencycompose.crypto_listing.presentation.crypto_chart

import com.example.cryptocurrencycompose.crypto_listing.domain.model.CryptoInfoModel

data class CryptoInfoState(
    val yearButtonPushed : Boolean = false,
    val monthButtonPushed : Boolean = false,
    val weekButtonPushed : Boolean = false,
    val dayButtonPushed : Boolean = false,
    val cryptoInfo : List<CryptoInfoModel> = emptyList(),
    val error : String = "",
)
