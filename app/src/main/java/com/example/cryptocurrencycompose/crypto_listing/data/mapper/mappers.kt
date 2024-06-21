package com.example.cryptocurrencycompose.crypto_listing.data.mapper

import com.example.cryptocurrencycompose.crypto_listing.data.local.CryptoListingsEntity
import com.example.cryptocurrencycompose.crypto_listing.data.remote.CryptoInfoDataDto
import com.example.cryptocurrencycompose.crypto_listing.data.remote.CryptoListingDataDto
import com.example.cryptocurrencycompose.crypto_listing.data.util.CryptoInfoFormatter
import com.example.cryptocurrencycompose.crypto_listing.domain.model.CryptoInfoModel
import com.example.cryptocurrencycompose.crypto_listing.domain.model.CryptoModel


fun CryptoListingDataDto.toCryptoEntity() : CryptoListingsEntity{
    return CryptoListingsEntity(
        cryptoId = id,
        symbol = symbol,
        name = name,
        price = price,
        percentage = percentage
    )
}

fun CryptoListingDataDto.toCryptoModel() : CryptoModel{
    return CryptoModel(
        id = id,
        symbol = symbol,
        name = name,
        price = price,
        percentage = percentage
    )
}

fun CryptoListingsEntity.toCryptoModel() : CryptoModel{
    return CryptoModel(
        id = cryptoId,
        symbol = symbol,
        name = name,
        price = price,
        percentage = percentage
    )
}

fun CryptoModel.toCryptoListingsEntity() : CryptoListingsEntity{
    return CryptoListingsEntity(
        cryptoId = id,
        symbol = symbol,
        name = name,
        price = price,
        percentage = percentage
    )
}

fun CryptoInfoDataDto.toCryptoInfoModel() : CryptoInfoModel{

    val formatter = CryptoInfoFormatter()
    val formattedTime = formatter.getHourFromTimestamp(time)
    val formattedPrice = formatter.formatPrice(price)

    return CryptoInfoModel(
        price = formattedPrice,
        time = formattedTime,
        date = date
    )
}