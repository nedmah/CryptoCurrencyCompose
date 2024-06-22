package com.example.cryptocurrencycompose.crypto_listing.data.mapper

import com.example.cryptocurrencycompose.crypto_listing.data.local.CryptoListingsEntity
import com.example.cryptocurrencycompose.crypto_listing.data.remote.CryptoInfoDataDto
import com.example.cryptocurrencycompose.crypto_listing.data.remote.CryptoListingDataDto
import com.example.cryptocurrencycompose.crypto_listing.data.util.CryptoInfoFormatter
import com.example.cryptocurrencycompose.crypto_listing.domain.model.CryptoInfoModel
import com.example.cryptocurrencycompose.crypto_listing.domain.model.CryptoModel
import java.util.Calendar


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

    val formattedPrice = CryptoInfoFormatter().formatPrice(price)
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1 // Months are indexed from 0
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)

    return CryptoInfoModel(
        price = formattedPrice,
        year = year,
        month = month,
        day = day,
        time = hour,
        date = date
    )
}