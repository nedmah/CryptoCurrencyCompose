package com.example.cryptocurrencycompose.crypto_listing.data.mapper

import com.example.cryptocurrencycompose.crypto_listing.data.local.CryptoListingsEntity
import com.example.cryptocurrencycompose.crypto_listing.data.remote.CryptoDataDto
import com.example.cryptocurrencycompose.crypto_listing.domain.model.CryptoModel


fun CryptoDataDto.toCryptoEntity() : CryptoListingsEntity{
    return CryptoListingsEntity(
        symbol = symbol,
        name = name,
        price = price,
        percentage = percentage
    )
}

fun CryptoDataDto.toCryptoModel() : CryptoModel{
    return CryptoModel(
        symbol = symbol,
        name = name,
        price = price,
        percentage = percentage
    )
}

fun CryptoListingsEntity.toCryptoModel() : CryptoModel{
    return CryptoModel(
        symbol = symbol,
        name = name,
        price = price,
        percentage = percentage
    )
}

fun CryptoModel.toCryptoListingsEntity() : CryptoListingsEntity{
    return CryptoListingsEntity(
        symbol = symbol,
        name = name,
        price = price,
        percentage = percentage
    )
}